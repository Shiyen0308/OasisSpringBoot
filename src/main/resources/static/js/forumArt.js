 $(document).ready(function () {
		    	// 載入 navbar 和 forumHeader
	            $("#navbar").load("/home/navbar.html");
	            $("#forumHeader").load("/art/forumHeader.html");
		    	 
	            $('.dataTables_filter input[type="search"]').css('background-color', '#f0f8ff');
		        const currentUrl = window.location.pathname;
		        const gameIdMatch = currentUrl.match(/\/forum\/([^\/]+)/); 

		        let gameId;

		        if (gameIdMatch && gameIdMatch[1]) {
		            gameId = gameIdMatch[1]; 
		            localStorage.setItem('gameId', gameId);
		        } else {
		            console.error('無法取得 gameId');
		            return; 
		        }
				// 獲取文章
		        $.ajax({
		            url: `/api/forum/${gameId}`,  
		            type: "Get",
		            contentType: "application/json",
		            dataType: "json",
		            success: function (data) {
		            	 $("#artTable tbody").empty(); 
		                $.each(data, function (index, artDTO) {
		                	
		                    let art = "";
		                    art += `
		                        <tr>
		                        <td><a href="/forum/${gameId}/art/${artDTO.artId}" class="artTitle">${artDTO.artTitle}</a></td>
		                        <td>${artDTO.artTimestamp}</td>
		                        <td>${artDTO.userNickname}</td>
	                    		</tr>
		                		`;

		                    $("#artTable tbody").append(art);
		                });

				            },
		            error: function (xhr, status, error) {
		                console.error('AJAX request failed:', error);
		            }
		        });
		      
                // 初始化 DataTables
                
                $('#artTable').DataTable({
                    "info": false,
                    "lengthMenu": [],
                    "searching": false,
                    "columns": [
                        { "width": "60%" },
                        { "width": "20%" },
                        { "width": "20%" }
                    ],
                    "order": [[1, 'asc']],
                    autoWidth: false
                });

		    });
		 <!--
		  // 搜尋文章
		  $(document).ready(function() {
		        $('#search-button').click(function() {
		            const keyword = $('#search-input').val();
					let gameId = localStorage.getItem('gameId');
		        
		            $.ajax({
		                url: '/api/search', 
		                type: 'GET',
		                data: { 
		                	keyword: keyword ,
	                		gameId : gameId
		                	}, 
		                success: function(response) {
		                    
		                    
		                        
		                   
		                },
		                error: function(xhr, status, error) {
		                    console.error('發生錯誤:', error);
		                }
		            });
		        });
		    });  
		 -->
