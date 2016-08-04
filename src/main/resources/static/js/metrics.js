$( document ).ready(function() {

  function refreshLiveMetricsTable(){
    $.ajax({
      url: '/obdmetrics'
    })
    .done(function(data){
      $("#metricsTable tbody").empty();
      $.each(data, function(i,metric) {
        $("#metricsTable tbody").append("<tr><td>"
        + metric.metricName  + "</td><td>"
        + metric.value + " "
        + metric.unit + "</tr>");
      });
    });
  }

  function enableAutoRefreshAllMetrics() {
    $.ajax({
      url: '/metricsettings'
    })
    .done(function(metrics){
      $.each(metrics.disabledMetrics, function(index, disabledMetric){
        metrics.enabledMetrics.push(disabledMetric);
      });
      metrics.disabledMetrics = [];
      $.ajax({
        url: '/metricsettings',
        method: "PUT",
        contentType : 'application/json',
        data: JSON.stringify(metrics)
      }).done(function(){
        refreshLiveMetricsTable();
        window.setInterval(refreshLiveMetricsTable, 5000);
      });
    });
  }
  enableAutoRefreshAllMetrics();

  var liveChartData = {
    chart1 : {
      metricName : "ENGINE_RPM",
      data : [
        ['x'],
        ['ENGINE_RPM']
      ]

    },

    chart2 : {
      metricName : "ENGINE_RPM",
      data : [
        ['x'],
        ['ENGINE_RPM']
      ]

    }

  };

  for(var i=0; i < 20; i++){
    liveChartData.chart1.data[0].push( (new Date).getTime());
    liveChartData.chart1.data[1].push(0);
    liveChartData.chart2.data[0].push( (new Date).getTime());
    liveChartData.chart2.data[1].push(0);
  }

  function liveChart(chartId, metricName) {
    var chartSpecificData = liveChartData[chartId];
    chartSpecificData.metricName = metricName;
    chartSpecificData.data[1][0] = metricName;
    var generatedChart = c3.generate({
      bindto: '#' + chartId,
      data: {
        x: 'x',
        columns: chartSpecificData.data,
      },
      axis: {
        x: {
          type: 'timeseries',
          tick: {
            format:  function (x) { return ''; },
          }
        }
      }
    });
    window.setInterval(function(){
      $.ajax({
        url: '/obdmetrics/' + chartSpecificData.metricName,
      }).done(function(data){
        chartSpecificData.data[0].splice(1, 1);
        chartSpecificData.data[0].push( (new Date).getTime());
        chartSpecificData.data[1].splice(1, 1);
        chartSpecificData.data[1].push(data.value);
        generatedChart.flow({
          columns: chartSpecificData.data
        });
      });
    }, 2000);
  }

  liveChart('chart1', 'ENGINE_RPM');
  liveChart('chart2', 'ENGINE_OIL_TEMP');

});
