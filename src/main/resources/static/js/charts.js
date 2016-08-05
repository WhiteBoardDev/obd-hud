$( document ).ready(function() {

  function createGauge(chartId, metricName){
    $('#' + chartId + '_label').val(metricName);
    var mostRecentData = {
      value : 0,
      unit : 'na'
    }
    var chart = c3.generate({
      bindto: '#' + chartId,
      data: {
        columns: [
          ['data', 0]
        ],
        type: 'gauge'
      },
      gauge: {
        label: {
          format: function(value, ratio) {
            return mostRecentData.value + ' ' + mostRecentData.unit;
          },
          //            show: false // to turn off the min/max labels.
        },
        //    min: 0, // 0 is default, //can handle negative min e.g. vacuum / voltage / current flow / rate of change
        //    max: 100, // 100 is default
        units: ' ',
        //    width: 39 // for adjusting arc thickness
      },
      color: {
        pattern: ['#FF0000', '#F97600', '#F6C600', '#60B044'], // the three color levels for the percentage values.
        threshold: {
          //            unit: 'value', // percentage is default
          //            max: 200, // 100 is default
          values: [30, 60, 90, 100]
        }
      }
      // size: {
      //     height: 180
      // }
    });
    window.setInterval(function(){
      $.ajax({
        url: '/obdmetrics/' + metricName,
      }).done(function(data){
        mostRecentData.value = data.value;
        mostRecentData.unit = data.unit;
        chart.load({
          columns: [['data', data.value]],
          units: data.unit
        });
      });
    }, 2000);

  }

  function liveLineChart(chartId, metricName) {
  $('#' + chartId + '_label').val(metricName);
    var liveChartData = [
      ['x'],
      [metricName]
    ];
    //default data
    for(var i=0; i < 20; i++){
      liveChartData[0].push( (new Date).getTime());
      liveChartData[1].push(0);
    }

    var generatedChart = c3.generate({
      bindto: '#' + chartId,
      data: {
        x: 'x',
        columns: liveChartData,
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
        url: '/obdmetrics/' + metricName,
      }).done(function(data){
        liveChartData[0].splice(1, 1);
        liveChartData[0].push( (new Date).getTime());
        liveChartData[1].splice(1, 1);
        liveChartData[1].push(data.value);
        generatedChart.flow({
          columns: liveChartData
        });
      });
    }, 2000);
  }


  function getParameterByName(name) {
    var url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
    results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
  }

  function createChart(chartType, chartId, chartMetric){
    if(!chartType) chartType = 'gauge';

    if(chartType == 'line') {
      liveLineChart(chartId, chartMetric);
    }
    if(chartType == 'gauge') {
      createGauge(chartId, chartMetric);
    }
  }

  var chartDefaults = [
    {},
    {
      id: 'chart1',
      type: 'gauge',
      metric: 'THROTTLE_POS'
    },
    {
      id: 'chart2',
      type: 'gauge',
      metric: 'CONTROL_MODULE_VOLTAGE'
    },
    {
      id: 'chart3',
      type: 'line',
      metric: 'ENGINE_COOLANT_TEMP'
    },
  ];


  $.ajax({
    url: '/obdmetricnames'
  })
  .done(function(data){
    $.each(data, function(index, availableMetricName) {
      $('.allMetrics')
      .append($("<option></option>")
      .text(availableMetricName)
      .attr("key", availableMetricName))
    });

    for(var i = 1; i < 4; i++){
      var chartMetric = getParameterByName('chart' + i + '-metric');
      var chartType = getParameterByName('chart' + i + '-type');
      if(chartMetric) {
        createChart(chartType, 'chart' + i, chartMetric);
      }else {
        var defaultChart = chartDefaults[i];
        createChart(defaultChart.type, defaultChart.id, defaultChart.metric);
      }
    }
  });

});
