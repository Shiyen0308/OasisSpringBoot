let currentPage = 1; // 當前頁碼
const resultsPerPage = 6; // 每頁顯示的結果數量
let totalResults = 0; // 總結果數

$(document).ready(function() {
    $('#search-button').click(function() {
        const keyword = $('#search-input').val();
        currentPage = 1; 
        searchPosts(keyword, currentPage);
    });

    $('#next-button').click(function() {
        currentPage++;
        const keyword = $('#search-input').val();
        searchPosts(keyword, currentPage);
    });

    $('#prev-button').click(function() {
        currentPage--;
        const keyword = $('#search-input').val();
        searchPosts(keyword, currentPage);
    });
});

// 搜尋函數
function searchPosts(keyword, page) {
	let gameId = localStorage.getItem('gameId');
    $.ajax({
        url: '/api/search', 
        type: 'GET',
        data: { 
		keyword: keyword,
		gameId : gameId, 
        page: page, 
        size: resultsPerPage }, 
        success: function(response) {
            totalResults = response.totalElements; 
            displayResults(response.content); 
            updatePagination(); 
        },
        error: function(xhr, status, error) {
            console.error('發生錯誤:', error);
        }
    });
}

// 顯示搜尋結果
function displayResults(posts) {
    $('#results').empty();
    if (posts.length > 0) {
        posts.forEach(post => {
            $('#results').append(`
                <div>
                    <h3>${post.title}</h3>
                    <p>${post.content}</p>
                </div>
            `);
        });
    } else {
        $('#results').append('<p>找不到相關文章。</p>');
    }
}

// 更新分頁按鈕
function updatePagination() {
    $('#page-info').text(`第 ${currentPage} 頁`);
    $('#prev-button').prop('disabled', currentPage === 1);
    $('#next-button').prop('disabled', currentPage * resultsPerPage >= totalResults);
}
