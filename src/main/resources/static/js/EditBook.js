$(function () {
    $.ajax(
        '/api/books/' + $('#editId').val(),
        {
            type: 'GET',
            success: function (book) {
                $("#name").val(book.name);
                $("#genre").val(book.name);
                $("#fio").val(book.author.fio);
                $("#year").val(book.author.year);
            }
        }
    );
});
$( document ).ready(function() {
    $('#editForm').submit(function (event) {
        event.preventDefault();
        $.ajax(
            '/api/books',
            {
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({
                    id: $('#editId').val(),
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
    $('#deleteForm').submit(function (event) {
        event.preventDefault();
        $.ajax(
            '/api/books/' + $('#deleteId').val(),
            {
                type: 'DELETE',
                success: function (result) {
                    window.location.assign('/books/getAll');
                }
            }
        );
    });
});