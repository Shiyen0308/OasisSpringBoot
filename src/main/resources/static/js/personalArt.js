//獲取個人文章
        $(document).ready(function() {
        	// 載入 navbar 和 forumHeader
            $("#navbar").load("/home/navbar.html");
            $("#forumHeader").load("/art/forumHeader.html");
            $.ajax({
                url: '/api/getArtList',
                type: "GET",
                dataType: "json",
                success: function(data) {
                    const artTableBody = $('#artTableBody');
                    artTableBody.empty(); 

                    data.forEach(artVO => {
                    	console.log(artVO)
                        const artRow = `
                            <tr>
                                <td><a href="/forum/${artVO.gameId}/art/${artVO.artId}" class="artTitle">${artVO.artTitle}</a></td>
                                <td>${artVO.artTimestamp}</td>
                                <td>
                                <button class="update-button" data-artId=${artVO.artId}>修改</button>
                                </td>
                                <td>
                                <button class="delete-button" data-artId=${artVO.artId}>刪除</button>
                                </td>
                            </tr>
                        `;
                        artTableBody.append(artRow);
                    });
                    $(document).on('click', '.update-button', function() {
                        
                    	var artId = $(this).data('artid'); 
                        updateArt(artId);
                    });
					$(document).on('click', '.delete-button', function() {
                        
                    	var artId = $(this).data('artid'); 
                        deleteArt(artId);
                    });
                },
                error: function(xhr, status, error) {
                    console.error("獲取文章資料失敗", status, error);
                }
            });
        });
    // 修改文章
        function updateArt(artId) {
        	$.ajax({
                url: `/api/art/${artId}`, 
                type: 'GET',
                contentType: "application/json",
	            dataType: "json",
                success: function(data) {
                    
                    if (data) {
                       
                        sessionStorage.setItem('artData', JSON.stringify(data));
                        
                      
                        window.location.href = '/update?artId=' + artId;
                    } else {
                        alert('獲取文章資料失敗');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Error:', error);
                    alert('獲取文章資料時發生錯誤');
                }
            });
        }
       
	//刪除文章
        function deleteArt(artId) {
            $.ajax({
                url: '/api/delete',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({ artId: artId }),
                success: function(data) {
                	console.log(data)
                    if (data) {
                        alert('文章刪除成功');
                    } else {
                        alert('文章刪除失敗');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Error:', error);
                }
            });
        }
