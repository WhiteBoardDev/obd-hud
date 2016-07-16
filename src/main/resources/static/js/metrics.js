$( document ).ready(function() {
  //refresh metrics
  window.setInterval(function(){
    $.ajax({
      url: '/obdmetrics'
    })
    .done(function(data){
      $('#rawMetrics').text(JSON.stringify(data));
    });
  }, 5000);

$('#enableAllMetrics').click(function() {
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
      dataType: 'json',
      contentType : 'application/json',
      data: JSON.stringify(metrics)
    });
  });
});



});
