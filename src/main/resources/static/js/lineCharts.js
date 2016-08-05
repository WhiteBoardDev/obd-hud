$( document ).ready(function() {

  function liveChart(chartId, metricName) {
    $('#' + chartId + '_label').text(metricName);
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

  //liveChart('chart1', 'ENGINE_RPM');
  //liveChart('chart2', 'SPEED');

  liveChart('chart3', 'ENGINE_COOLANT_TEMP');

});
