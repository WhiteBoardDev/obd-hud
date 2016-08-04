$( document ).ready(function() {
  function updateConnectionState(){
    $.ajax({ url: "/connection"})
    .done(function(data) {
      if(data.connected) {
        $('#connectionStatus').text("Connected")
      } else {
        $('#connectionStatus').text("NOT Connected")
      }
      if(!data.connectedPort){
        data.connectedPort = 'N/A';
      }
      $('#connectedPort').text(data.connectedPort);
      $.each(data.availablePorts, function(index, availablePort) {
        $('#availableConnectionPorts')
        .append($("<option></option>")
        .text(availablePort)
        .attr("key", availablePort))
      });
    });
  }

  updateConnectionState();

  $('#connectButton').click(function(){
    var selectedPort = $('#availableConnectionPorts').find(":selected").text();
    $.ajax({
      method: "POST",
      url: "/connection/connect",
      contentType : 'application/json',
      data: JSON.stringify({ port: selectedPort })
    })
    .done(function( msg ) {
      updateConnectionState();
    });
  });

});
