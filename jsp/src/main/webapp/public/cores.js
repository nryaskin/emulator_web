$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'core',
        success: function(data){
        $.each(data, function( index, value ) {
                var row = $("<tr><td>" + value.coreName + "</td><td>" + value.path + "</td></tr>");
                $("#coreTable").append(row);
            });
        },
        error: function(jqXHR, textStatus, errorThrown){
                alert('error: ' + textStatus + ': ' + errorThrown);
            }
        });
        $( "#dialog" ).dialog({
                                    autoOpen: false,
                                    show: {
                                      effect: "blind",
                                      duration: 1000
                                    },
                                    hide: {
                                      effect: "explode",
                                      duration: 1000
                                    }
                                  });
    $('.coreCreate').on('click', function(){
        $( "#dialog" ).dialog( "open" );
    });
});