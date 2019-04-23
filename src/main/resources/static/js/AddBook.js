$( document ).ready(function() {
    $('#addForm').submit(function (event) {
        event.preventDefault();
        $.ajax(
            '/api/books',
            {
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    name: $('#name').val(),
                    genre: $('#genre').val(),
                    fio: $('#fio').val(),
                    year: $('#year').val()
                }),
                success: function (result) {
                    window.location.assign('/books/getAll');
                }
            }
        );
    });
});