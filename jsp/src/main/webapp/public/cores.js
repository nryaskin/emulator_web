$(document).ready(function() {

    var dialog, editDialog, form,
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
    var rest_url = 'core' + '/' + id;
        $.ajax({
                    type: 'DELETE',
                    url: rest_url,
                    success: function(){
                        updateCores();
                    },
                    error: function(jqXHR, textStatus, errorThrown){
                        alert('error: ' + textStatus + ': ' + errorThrown);
                    }
        });
    }

    function deleteProfile(id) {
        var rest_url = 'profile' + '/' + id;
            $.ajax({
                        type: 'DELETE',
                        url: rest_url,
                        success: function(){
                            updateProfiles();
                        },
                        error: function(jqXHR, textStatus, errorThrown){
                            alert('error: ' + textStatus + ': ' + errorThrown);
                        }
            });
    }

    function enableDelete() {
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
        $("button[coreCrud='delete-core']" ).button().on( "click", function() {
            var id = $(this).attr("CoreID");
            confirmationDialog.data('core-id', id).dialog( "open" );
        });
    }

    function editCore(id) {
        var cname = $( "#cname" );
        var cpath = $( "#cpath" );
        var rest_url = 'core' + '/' + id;
        var data = {};
        data.id = id;
        data.coreName = cname.val();
        data.corePath = cpath.val();
        $.ajax({
            type: 'PUT',
            url: rest_url,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function(inData){
                updateCores();
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert('error: ' + textStatus + ': ' + errorThrown);
            }
        });
    }

    function enableEdit() {
        editDialog = $('#edit-dialog-form').dialog(
            {
            autoOpen: false,
            height: 400,
            width: 350,
            modal: true,
            open: function( event, ui ) {
                var id = editDialog.data('core-id');
                var rest_url = 'core' + '/' + id;
                $.ajax({
                    type: 'GET',
                    url: rest_url,
                    success: function(core){
                        $( "#cname" ).val(core.coreName);
                        $( "#cpath" ).val(core.corePath);
                    },
                    error: function(jqXHR, textStatus, errorThrown){
                        alert('error: ' + textStatus + ': ' + errorThrown);
                    }
                });
            },
            buttons: {
            "Edit": function() {
                var id = editDialog.data('core-id');
                editCore(id);
                editDialog.dialog( "close" );
            },
            Cancel: function() {
                editDialog.dialog( "close" );
            }
            }
        });

        $( "button[coreCrud='edit-core']" ).button().on( "click", function() {
            var id = $(this).attr("CoreID");
            editDialog.data('core-id', id).dialog( "open" );
        });
        editDialog.find( "form" ).on( "submit", function( event ) {
            event.preventDefault();
        });
    }

    function editProfile(id) {
        var cname = $( "#pname" );
        var newCoreId = $('#profile-cores-edit').find(":selected").val();
        var pr_rest_url = 'profile' + '/' + id;
        var coreRestUrl = 'core/' + newCoreId;
        var data = {};
        data.id = id;
        data.profileName = cname.val();
        var on_get_core = function (data) {
            $.ajax({
                type: 'PUT',
                url: pr_rest_url,
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                success: function(inData){
                    updateProfiles();
                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('error: ' + textStatus + ': ' + errorThrown);
                }
            });
        };

        $.ajax({
            type: 'GET',
            url: coreRestUrl,
            success: function(core){
                data.core = core;
                on_get_core(data);
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert('error: ' + textStatus + ': ' + errorThrown);
            }
        });

    }

    function enableProfileEdit() {

        var fillSelector = function(id) {
            $.ajax({
                type: 'GET',
                url: 'core',
                success: function(data){
                    $("#profile-cores-edit").empty();
                    $.each(data, function( index, value ) {
                        var select = $('<option value="' + value.id + '">' + value.coreName + "</option>");
                        $("#profile-cores-edit").append(select).val(id);
                    })},
                error: function(jqXHR, textStatus, errorThrown){
                            alert('error: ' + textStatus + ': ' + errorThrown);
                }
            });
        };

        var profileEditDialog = $('#edit-profile-dialog-form').dialog(
        {
            autoOpen: false,
            height: 400,
            width: 350,
            modal: true,
            open: function( event, ui ) {
                var id = profileEditDialog.data('profile-id');
                var rest_url = 'profile' + '/' + id;
                $.ajax({
                    type: 'GET',
                    url: rest_url,
                    success: function(profile){
                        $( "#pname" ).val(profile.profileName);
                        fillSelector(profile.core.id);
                    },
                    error: function(jqXHR, textStatus, errorThrown){
                        alert('error: ' + textStatus + ': ' + errorThrown);
                    }
                });
            },
            buttons: {
                "Edit": function() {
                    var id = profileEditDialog.data('profile-id');
                    editProfile(id);
                    profileEditDialog.dialog( "close" );
                },
                Cancel: function() {
                    profileEditDialog.dialog( "close" );
                }
            }
        });

        $( "button[profileCrud='edit-profile']" ).button().on( "click", function() {
            var id = $(this).attr("ProfileID");
            profileEditDialog.data('profile-id', id).dialog( "open" );
        });
        profileEditDialog.find( "form" ).on( "submit", function( event ) {
            event.preventDefault();
        });
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

                          enableEdit()
                          enableDelete();
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

    function enableProfileDelete()
    {
        var confirmationDialog = $( "#dialog-confirm" ).dialog({
            autoOpen: false,
            height: 400,
            width: 350,
            modal: true,
            buttons: {
                "Yes": function() {
                    var id = confirmationDialog.data('profile-id');
                    deleteProfile(id);
                    confirmationDialog.dialog( "close" );
                },
                Cancel: function() {
                    confirmationDialog.dialog( "close" );
                }
            }
        });
        $("button[profileCrud='delete-profile']" ).button().on( "click", function() {
            var id = $(this).attr("profileID");
            confirmationDialog.data('profile-id', id).dialog( "open" );
        });
    }

    function enableProfileCreate()
    {
        var profileCreateDialog = $('#profile-dialog-form').dialog(
                {
                autoOpen: false,
                height: 400,
                width: 350,
                modal: true,
                open: function( event, ui ) {
                    $.ajax({
                        type: 'GET',
                        url: 'core',
                        success: function(data){
                            $("#profile-cores").empty();
                            $.each(data, function( index, value ) {
                                var select = $('<option value="' + value.id + '">' + value.coreName + "</option>");
                                $("#profile-cores").append(select);
                            })},
                        error: function(jqXHR, textStatus, errorThrown){
                            alert('error: ' + textStatus + ': ' + errorThrown);
                        }
                    });
                },
                buttons: {
                "Create": function() {
                    var id = $('#profile-cores').find(":selected").val();
                    var core_url = 'core/' + id;
                    $.ajax({
                        type: "GET",
                        url: core_url,
                        success: function(core) {
                            var profile = {};
                            profile.profileName = $('#profName').val();
                            profile.core = core;
                            $.ajax({
                                type: "POST",
                                url: 'profile',
                                data: JSON.stringify(profile),
                                contentType: "application/json; charset=utf-8",
                                success: function() {
                                    updateProfiles();
                                }
                            })
                        },
                        error: function(jqXHR, textStatus, errorThrown){
                            alert('error: ' + textStatus + ': ' + errorThrown);
                        }
                    });
                    profileCreateDialog.dialog( "close" );
                },
                Cancel: function() {
                    profileCreateDialog.dialog( "close" );
                }
                }
            });

            $( "#profile-create" ).button().on( "click", function() {
                profileCreateDialog.dialog( "open" );
            });
            profileCreateDialog.find( "form" ).on( "submit", function( event ) {
                event.preventDefault();
            });
    }

    function updateProfiles() {
        $("#profiles tbody").empty();
        $.ajax({
            type: 'GET',
            url: 'profile',
            success: function(data){
                $.each(data, function( index, profile ) {
                    var row = $("<tr><td>" + profile.profileName + "</td>" +
                    "<td>" + profile.core.coreName + "</td>" +
                    "<td>" + "<button id='edit-profile" + profile.id + "' ProfileID='" + profile.id + "' profileCrud='edit-profile'>Edit</button>" + "</td>" +
                    "<td>" + "<button id='delete-profile" + profile.id +
                    "' profileID='" + profile.id + "' profileCrud='delete-profile'>Delete</button>" +
                    "</td>" + "</tr>");
                    $("#profiles tbody").append(row);
                });
                enableProfileEdit();
                enableProfileDelete();
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert('error: ' + textStatus + ': ' + errorThrown);
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
    enableEdit();
    enableProfileCreate();
    updateProfiles();
    enableProfileDelete();

    $( "#create-core" ).button().on( "click", function() {
          dialog.dialog( "open" );
     });

});
