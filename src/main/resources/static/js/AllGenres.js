$( document ).ready(function() {
    $(function () {
        $.get('/api/genres').done(function (genres) {
            genres.forEach(function (genre) {
                $('tbody').append(`
                    <tr>
                        <td>${genre.name}</td>
                        <td>
                            <a href="/genres/edit?name=${genre.name}">Редактировать</a>
                        </td>
                    </tr>
                `)
            });
        })
    });
});