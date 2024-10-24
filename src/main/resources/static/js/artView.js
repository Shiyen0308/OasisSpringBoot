 $(document).ready(function () {
            // 動態載入 navbar 和 forumHeader
            $("#navbar").load("/home/navbar.html");
            $("#forumHeader").load("/art/forumHeader.html");

            const currentUrl = window.location.pathname;
	        const artIdMatch = currentUrl.match(/\/forum\/([^\/]+)\/art\/([^\/]+)/); 

	        let artId;

	        if (artIdMatch && artIdMatch[2]) {
	            artId = artIdMatch[2]; 
	        } else {
	            console.error('無法取得 artId');
	            return; 
	        }
			
	        $.ajax({
                url: `/api/art/${artId}`,
                type: "Get",
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    $('#firstUser').attr('src', data.userAvatar);
                    $("#userNickname").text(data.userNickname);
                    $("#artTitle").text(data.artTitle);
                    $("#artContent").text(data.artContent);
					console.log(data)
                    // 渲染留言區
                    $.each(data.messageDTO, function (index, message) {
                        let comment = `
                            <div class="oneMessage">
                                <div class="message">
                                    <div class="messageTop">
                                        <span class="messageUser">${message.userNickname}</span>
                                        <span class="messageContent">${message.messageContent}</span>
                                    </div>
                                    <div class="messageTimestamp">${message.messageTimestamp}</div>
                                </div>
                            </div>`;
                        $("#comment").append(comment);
                    });

                    // 生成留言表單
                    let inputForm = `
                        <form id="messageForm">
                            <input type="text" class="message" name="messageContent" required placeholder="留言...">
                            <button class="messageBtn" type="submit">留言</button>
                        </form>`;
                    $("#commentContainer").append(inputForm);
					
                   
                    $('#messageForm').submit(function (event) {
                        event.preventDefault(); 

                        let formData =  {
                        		messageContent: $('input[name="messageContent"]').val(),
                        		artId : artId
                        }
                       

                        $.ajax({
                            url: "/api/message", 
                            type: "POST",
                            contentType: "application/json",
                            data: JSON.stringify(formData),
                            success: function (data) {
                               
                                let newComment = `
                                    <div class="oneMessage">
                                        <div class="message">
                                            <div class="messageTop">
                                            <span class="messageUser">${data.userNickname}</span> 
                                            <span class="messageContent">${data.messageContent}</span>
                                        </div>
                                        <div class="messageTimestamp">${data.messageTimestamp}</div> 
                                        </div>
                                    </div>`;

                                
                                $("#comment").append(newComment);

                              
                                $('input[name="messageContent"]').val('');
                            },
                            error: function (xhr, status, error) {
                                console.error('留言提交失敗:', error);
                                
                                let errorMessage = "留言失敗，請稍後再試。"; 
                                if (xhr.status === 401) { 
                                    errorMessage = "未登入，請先登入再留言。";
                                }

                                alert(errorMessage);
                            }
                        });
                    });

                },
                error: function (xhr, status, error) {
                    console.error('AJAX request failed:', error);
                }
            });
           // 回覆
	        $.ajax({
                url: `/api/reply/${artId}`,
                type: "Get",
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                	$.each(data, function (index, artDTO) {
                		let artReply = `
                		    <div class="artContainer">
                		        <aside class="user">
                		            <div class="userAvatar">
                		                <img src= ${artDTO.userAvatar} alt="使用者頭像">
                		            </div>
                		            <div class="userNickname">${artDTO.userNickname}</div>
                		        </aside>
                		        <div class="art">
                		            <div class="artContent">${artDTO.artContent}</div>
                		            <div id="commentContainer${index}" class="commentContainer"> 
                		                <div id="comment${index}" class="comment"></div>
                		            </div>
                		        </div>
                		    </div>`;

                		$('body').append(artReply);
                    // 渲染留言區
                   
                    $.each(artDTO.messageDTO, function (index1, message) {
                      
                    	let comment1 = `
                            <div class="oneMessage">
                                <div class="message">
                                    <div class="messageTop">
                                        <span class="messageUser">${message.userNickname}</span>
                                        <span class="messageContent">${message.messageContent}</span>
                                    </div>
                                    <div class="messageTimestamp">${message.messageTimestamp}</div>
                                </div>
                            </div>`;
                            
                        $("#comment"+ index).append(comment1);
                    });

                    // 生成留言表單
                    let inputForm = `
                        <form id="messageForm" data-Id="${artDTO.artId}">
                            <input type="text" class="message" name="messageContent" required placeholder="留言...">
                            <button class="messageBtn" type="submit">留言</button>
                        </form>`;
                        $('#commentContainer'+ index).append(inputForm);
					
                   
                    $('form[data-Id]' ).submit(function (event) {
                        event.preventDefault(); 
                        
                        let artReplyId = $(this).attr('data-Id');
                        let messageContent = $(this).find('input[name="messageContent"]').val();
                        let formData =  {
                        		messageContent: messageContent,
                        		artId : artReplyId
                        }
                      	

                        $.ajax({
                            url: "/api/message", 
                            type: "POST",
                            contentType: "application/json",
                            data: JSON.stringify(formData),
                            success: function (data) {
                               
                                let newComment = `
                                    <div class="oneMessage">
                                        <div class="message">
                                            <div class="messageTop">
                                            <span class="messageUser">${data.userNickname}</span> 
                                            <span class="messageContent">${data.messageContent}</span>
                                        </div>
                                        <div class="messageTimestamp">${data.messageTimestamp}</div> 
                                        </div>
                                    </div>`;
                      
                                $("#comment" +index).append(newComment);
                                let formToClear = $(`form[data-id="${artReplyId}"]`);
                                formToClear.find('input[name="messageContent"]').val('');
                              
                            },
                            error: function (xhr, status, error) {
                                console.error('留言提交失敗:', error);
                                
                                let errorMessage = "留言失敗，請稍後再試。"; 
                                if (xhr.status === 401) { 
                                    errorMessage = "未登入，請先登入再留言。";
                                }

                                alert(errorMessage);
                            }
                        });
                    });
                	})
                },
                error: function (xhr, status, error) {
                    console.error('AJAX request failed:', error);
                }
            });
            

         

            
        });