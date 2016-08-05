$( document ).ready(function() {

  $(".chart-change-selector").change(function(data){
    chartId = data.target.getAttribute('chartId');
    var chartMetricKey = chartId + '-metric';
    var chartMetricValue = $(data.target).val();
    var chartTypeKey = chartId + '-type';
    var existingKeyValuesTmp = window.location.search.substr(1).split('&');
    var keyFound = false;
    $.each(existingKeyValuesTmp, function(index, keyValue){
      var keyValArray = keyValue.split('=');
      if(keyValArray.length == 2 && keyValArray[0] == chartMetricKey){
        keyFound = true;
        keyValArray[1] = chartMetricValue;
        existingKeyValuesTmp[index] = keyValArray.join('=');
      }
    });
    if(!keyFound){
      existingKeyValuesTmp.push(chartMetricKey + "="+ chartMetricValue);
    }
    window.location.href = window.location.pathname+"?"+ existingKeyValuesTmp.join('&')
  });

});
