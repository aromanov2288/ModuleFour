$( document ).ready(function() {
    $(function () {
        $.get('/api/books').done(function (books) {
            books.forEach(function (book) {
                $('tbody').append(`
                    <tr>
                        <td>${book.name}</td>
                        <td>${book.genre}</td>
                        <td>${book.author.fio}</td>
                        <td>${book.author.year}</td>
                        <td>
                            <a href="/books/edit?id=${book.id}">Редактировать</a>
                        </td>
                    </tr>
                `)
            });
        })
    });
});