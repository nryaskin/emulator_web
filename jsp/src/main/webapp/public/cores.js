$(document).ready(function() {

    var dialog, form,
    name = $( "#name" ),
    path = $( "#path" ),
    allFields = $( [] ).add( name ).add( path ),
    tips = $( ".validateTips" );


    function updateTips( t ) {
          tips
            .text( t )
            .addClass( "ui-state-highlight" );
          setTimeout(function() {
            tips.removeClass( "ui-state-highlight", 1500 );
          }, 500 );
    }

    function checkLength( o, n, min, max ) {
          if ( o.val().length > max || o.val().length < min ) {
            o.addClass( "ui-state-error" );
            updateTips( "Length of " + n + " must be between " +
              min + " and " + max + "." );
            return false;
          } else {
            return true;
          }
    }

    function checkRegexp( o, regexp, n ) {
          if ( !( regexp.test( o.val() ) ) ) {
            o.addClass( "ui-state-error" );
            updateTips( n );
            return false;
          } else {
            return true;
          }
    }

    function deleteCore(id) {
        alert(id);
    }

    function updateCores() {
                $("#cores tbody").empty();
                $.ajax({
                      type: 'GET',
                      url: 'core',
                      success: function(data){
                      $.each(data, function( index, value ) {
                              var row = $("<tr><td>" + value.coreName + "</td><td>" + value.corePath + "</td>" +
                                            "<td>" + "<button id='edit-core" + value.id + "' CoreID='" + value.id + "' coreCrud='edit-core'>Edit</button>" + "</td>" +
                                           "<td>" + "<button id='delete-core" + value.id + "' CoreID='" + value.id + "' coreCrud='delete-core'>Delete</button>" + "</td></tr>");
                              $("#cores tbody").append(row);
                          });

                          $( "button[coreCrud='edit-core']" ).button().on( "click", function() {
                                dialog.dialog( "open" );
                          });

                          var confirmationDialog = $( "#dialog-confirm" ).dialog({
                                                         autoOpen: false,
                                                         height: 400,
                                                         width: 350,
                                                         modal: true,
                                                         buttons: {
                                                           "Yes": function() {
                                                                var id = confirmationDialog.data('core-id');
                                                                deleteCore(id);
                                                                confirmationDialog.dialog( "close" );
                                                           },
                                                           Cancel: function() {
                                                                confirmationDialog.dialog( "close" );
                                                           }
                                                         }
                                                       });
                          $( "button[coreCrud='delete-core']" ).button().on( "click", function() {
                              var id = $(this).attr("CoreID");
                              confirmationDialog.data('core-id', id).dialog( "open" );
                          });
                      },
                      error: function(jqXHR, textStatus, errorThrown){
                              alert('error: ' + textStatus + ': ' + errorThrown);
                      }
              });
    }

    function addCore() {
      var valid = true;
      allFields.removeClass( "ui-state-error" );

      valid = valid && checkLength( name, "core name", 3, 16 );
      valid = valid && checkLength( path, "path", 6, 80 );

      valid = valid && checkRegexp( path, /^\.?(\.)?(\/?[a-zA-z0-9]+)+/ig, "Use unix path" );

      if ( valid ) {
        var data = {};
        data.coreName = name.val();
        data.corePath = path.val();

      $.ajax({
              type: 'POST',
              url: 'core',
              data: JSON.stringify(data),
              contentType: "application/json; charset=utf-8",
              success: function(inData){
                  updateCores();
              },
              error: function(jqXHR, textStatus, errorThrown){
                      alert('error: ' + textStatus + ': ' + errorThrown);
                  }
              });
        dialog.dialog( "close" );
      }
      return valid;
    }


    function enableDeletion() {
        $( "#dialog-confirm" ).dialog({
              resizable: false,
              height: "auto",
              width: 400,
              modal: true,
              buttons: {
                "Delete all items": function() {
                  $( this ).dialog( "close" );
                },
                Cancel: function() {
                  $( this ).dialog( "close" );
                }
              }
            });
    }

    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 400,
      width: 350,
      modal: true,
      buttons: {
        "Create Core": addCore,
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
        allFields.removeClass( "ui-state-error" );
      }
    });

    form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
    });

    updateCores();


    $( "#create-core" ).button().on( "click", function() {
          dialog.dialog( "open" );
     });

});