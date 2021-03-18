

$(document).ready(function () {

    $('.eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user) {
           $('#edituser #editID').val(user.id);
           $('#edituser #editFN').val(user.firstname);
           $('#edituser #editLN').val(user.lastname);
           $('#edituser #editAge').val(user.age);
           $('#edituser #editEmail').val(user.email);
        });

        $('#edituser').modal();
    });


    $('.deleteBtn').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');
        $('#DeleteButton').attr('href', href);

        $('#deleteUser').modal();
    });
});
