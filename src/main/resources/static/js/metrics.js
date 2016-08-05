$( document ).ready(function() {

  function refreshLiveMetricsTable(){
    $.ajax({
      url: '/obdmetrics'
    })
    .done(function(data){
      $("#metricsTable tbody").empty();
      $.each(data, function(i,metric) {
        var secondColumnValue = metric.text
        if(metric.value) {
          secondColumnValue = metric.value + " " + metric.unit;
        }

        $("#metricsTable tbody").append("<tr><td>"
        + metric.metricName  + "</td><td>" + secondColumnValue + "</td></tr>");
      });
    });
  }

  refreshLiveMetricsTable();
  window.setInterval(refreshLiveMetricsTable, 5000);






});
