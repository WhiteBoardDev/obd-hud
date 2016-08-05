$( document ).ready(function() {
  function createGauge(chartId, metricName){

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
    $('#' + chartId + '_label').text(metricName);
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
  createGauge('chart1', 'THROTTLE_POS');
  createGauge('chart2', 'CONTROL_MODULE_VOLTAGE');
});
