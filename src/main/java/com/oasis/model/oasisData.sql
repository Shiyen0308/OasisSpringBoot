CREATE DATABASE IF NOT EXISTS oasis;
USE oasis;
DROP TABLE IF EXISTS user_art_list;
DROP TABLE IF EXISTS user_favor_list;
DROP TABLE IF EXISTS art_message_list;
DROP TABLE IF EXISTS message; -- fk to user and art
DROP TABLE IF EXISTS favor; -- fk to user and art, both not apply yet
DROP TABLE IF EXISTS art; -- fk to user and game

DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS user;


set auto_increment_offset=1;
set auto_increment_increment=1; 



-- user table ---------------------------
CREATE TABLE user (
	user_id INT AUTO_INCREMENT NOT NULL,
    user_email VARCHAR(255) UNIQUE NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_nickname VARCHAR(20),
    user_avatar TINYTEXT,
    
    CONSTRAINT user_primary_key PRIMARY KEY (user_id)
);
INSERT INTO user (user_email, user_password, user_nickname, user_avatar)  
	VALUES ('mlaitw@gmail.com','1234','Mike', '/image/user/1.jpg');
INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('qaswedfr0308@gmail.com','123','qaswedfr0308', '/image/user/2.jpg');
INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('c22700101@gmail.com','810726','c22700101', '/image/user/3.jpg');
INSERT INTO user (user_email, user_password, user_nickname, user_avatar)  
	VALUES ('jasonlee40203006@gmail.com','40203006lee','jasonlee40203006', '/image/user/4.jpg');
INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('j29387465@gmail.com','123456','j29387465', '/image/user/5.jpg');

INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('superman@yahoo.com.tw','1111','超人', '/image/user/6.jpg');    
INSERT INTO user (user_email, user_password, user_nickname, user_avatar)  
	VALUES ('stranger@gmail.com','2222','路人', '/image/user/7.jpg');
INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('ironman@gmail.com','2222','鋼鐵人', '/image/user/8.jpg');
INSERT INTO user (user_email, user_password,user_nickname,user_avatar)  
	VALUES ('spiderman@gmail.com','2222','蜘蛛人','/image/user/9.jpg');
INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('batman@gmail.com','2222','蝙蝠俠','/image/user/10.jpg');

INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('clietSupport@riot.com','3333','拳頭', '/image/user/.jpg');
INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('beanfun@gmail.com','4444','gamania','/image/user/12.jpg');
INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('teamcherry@gmail.com','5555','cherry', '/image/user/13.jpg');
INSERT INTO user (user_email, user_password, user_nickname, user_avatar)  
	VALUES ('thebattlecat@ponos.com','5555','貓咪大戰爭', '/image/user/14.jpg');
INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('service@nintendo.com','5555','任天堂', '/image/user/15.jpg');
INSERT INTO user (user_email, user_password,user_nickname, user_avatar)  
	VALUES ('service@blizzard.com','5555', '暴雪', '/image/user/16.jpg');



-- game table ------------------------
CREATE TABLE game (
	game_id INT AUTO_INCREMENT NOT NULL,
    game_name VARCHAR(20) UNIQUE NOT NULL,
    game_img TINYTEXT,
    game_created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT game_primary_key PRIMARY KEY (game_id)
);
INSERT INTO game (game_name,game_img) VALUES ('紅色警戒','/image/home/gameImg/紅色警戒.jpg');
INSERT INTO game (game_name,game_img) VALUES ('星海爭霸','/image/home/gameImg/星海爭霸.jpg');
INSERT INTO game (game_name,game_img) VALUES ('英雄聯盟','/image/home/gameImg/英雄聯盟.jpg');
INSERT INTO game (game_name,game_img) VALUES ('傳說對決','/image/home/gameImg/傳說對決.jpg');
INSERT INTO game (game_name,game_img) VALUES ('貓咪大戰爭','/image/home/gameImg/貓咪大戰爭.png');
INSERT INTO game (game_name,game_img) VALUES ('楓之谷','/image/home/gameImg/楓之谷.jpg');
INSERT INTO game (game_name,game_img) VALUES ('空洞騎士','/image/home/gameImg/空洞騎士.jpg');
INSERT INTO game (game_name,game_img) VALUES ('艾爾登法環','/image/home/gameImg/艾爾登法環.jpg');
INSERT INTO game (game_name,game_img) VALUES ('薩爾達傳說曠野之息','/image/home/gameImg/薩爾達傳說曠野之息.jpg');
INSERT INTO game (game_name,game_img) VALUES ('動物森友會','/image/home/gameImg/動物森友會.jpg');
INSERT INTO game (game_name,game_img) VALUES ('鬥陣特攻','/image/home/gameImg/鬥陣特攻.jpg');
INSERT INTO game (game_name,game_img) VALUES ('絕對武力','/image/home/gameImg/絕對武力.jpg');
INSERT INTO game (game_name,game_img) VALUES ('決勝時刻','/image/home/gameImg/決勝時刻.jpg');
INSERT INTO game (game_name,game_img) VALUES ('阿爾比恩','/image/home/gameImg/阿爾比恩.jpg');
INSERT INTO game (game_name,game_img) VALUES ('生死格鬥','/image/home/gameImg/生死格鬥.jpg');


-- art table --------------------------
CREATE TABLE ART (
	art_id	INT AUTO_INCREMENT NOT NULL,
	art_title VARCHAR(255),
	art_content MEDIUMTEXT,
	art_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
	art_reply INT,
    art_status INT,
    art_user_id INT,
    art_game_id INT,
    CONSTRAINT  art_user_id FOREIGN KEY (art_user_id) REFERENCES user (user_id),
    CONSTRAINT  art_game_id FOREIGN KEY (art_game_id) REFERENCES game (game_id),
	CONSTRAINT art_primary_key PRIMARY KEY (art_id)
)AUTO_INCREMENT = 1;
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)  VALUES ("【問題】大g賽恩思路問題","最近玩了20把左右
單雙彈性快打都有打
為什麼十分鐘破了首塔
隊友還是能崩線
對面打野都來抓我
我方打野那時候都不會去拿中立資源
也不會去幫其他路 就只顧著吃野
重點是整體經濟打開biltz 插件看 是領先的
但大概有2/3的局都輸掉
不太懂輸在哪裡
對局影片檔只能發YT 看嗎
還是有什麼方法可以給各位高手解惑嗎", "2024-06-05 22:42:20",NULL,0,1,3);
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   VALUES ("【心得】這什麼白癡首頁","不是我要嘴
只要開著這個首頁，我的CPU可以多飆50%
整體衝上80~100%
只要開著個人資料什麼的就會降。

我就想說怎麼最近LOL掛著CPU就會急速運轉
原來是那個新首頁搞的

真的很爛。

上次那個戰魂鬥士吃爆記憶體。
這次吃爆CPU，都快懷疑首頁在挖礦了。","2024-06-04 11:42:04",Null,0,2,3);
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   VALUES ("【心得】這什麼白癡首頁","客戶端平台真的需要整個打掉重寫

不然裡面肯定積累了10多年來的垃圾程式語法","2024-06-04 13:36:23",2,0,8,3);
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   VALUES ("【心得】這什麼白癡首頁","我是舊電腦
昨天開始開遊戲然後會爆cpu重新開機
以為電腦真的太舊要換
原來是這破首頁
riot真的爛死","2024-06-04 13:41:30",2,0,10,3);
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   VALUES("【心得】這什麼白癡首頁","改成陸配介面 沒煩惱 ..

直接規避掉實際  以前G社時期就是了  每次打開都一堆垃圾網頁

所以我已經養成習慣  直接改掉

","2024-06-04 13:43:21",2,0,11,3);
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   VALUES("【討論】英雄聯盟官方的桌布程式","想問問各位 有沒有人使用官方的桌布程式只停留在薩蜜拉 靈戰的桌布後就沒再更新了 是我的電腦沒辦法再更新了還是就真的官方也沒再用這桌布繼續更新 新的桌布圖呢","2024-06-05 21:00:17",NULL,0,4,3);
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   
VALUES(
	"【閒聊】聊聊這次v13.4貓戰角色的「強化」"
    ,"每當有改版消息釋出，玩家們總會熱烈討論，例如新增的超激角色能力是什麼? 又有哪些舊角色得到強化? 同時，什麼角色能稱作泛用，什麼角色可能注定是倉管，或是已成為時代眼淚，這些也會在我腦海裡浮現。

這篇主旨是從一個相對客觀的視角，討論玩家對角色強化的看法。
"
	,"2024-06-05 13:43:21"
    ,null -- reply
    ,0 -- status
    ,11 -- userid
    ,5); -- gameid
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   
VALUES(
	"【心得】新手組隊思路Q&A"
    ,"前言
hi，大家好，這裡是Royal，今天來簡單做下新手組隊思路的Q&A
在文章開始之前，我建議新手去看一下煎餅好吃大大的這則影片和這篇文章，看完基本上就有概念聽得懂我在講什麼了
————
Q：貓戰有泛用隊嗎？
A：首先貓戰沒有所謂的泛用隊，因為每關的角色需求截然不同，就憑這點，新手就不可能一隊走天下，頂多就只能放點主力角色，加上幾個位置靈活調整，不然你可以像7神那般，一個對他無敗編成不利的關卡，試幾百遍甚至過千遍，來尋找那0.1%的通關可能，反正我是不推薦新手這樣去試啦，只會消耗自己的遊戲熱情而已

Q：貓戰組隊時會特別依賴聯組嗎？
A：答案是依賴程度有限，因為聯組更多的是為了改良隊伍，填充隊伍不足／加強隊伍優勢的地方，我得說一句，如果你那個聯組對隊伍加成不大的話，請不要放任何聯組，你這麼做只是浪費隊伍格子而已，這裡加成不大的例子就是，比如你只是為了拉麵放研究力聯組，但實際上研究力加成對象真的太少，你不如拔掉研究力聯組，去放一些更有用的貓咪，狂腿／康康之類的，幾隻有用的貓咪，不比你塞個研究力好多了？

Q：貓戰組隊的角色練等有什麼優先順序嗎？
A：我自己私心覺得，稀有度跟強度其實是不成正比的，我見過太多新手只練超激，忽視掉一些低稀有度的好用角色，實際上老手們也不難發現，你在前期基本上練1－2隻超激大型非近戰角色，已經足夠應付通關需求，相比起超激，前期好用的狂亂（如魚、牆、腿）、以及經常說的三幻神（拉麵、美模etc.)等，會是較低成本，且也能幫助你通關的好選擇，所以刷首抽的各位請不要忽視稀有／激稀有，他們前期可能比你的超激更好用

Q：貓戰組隊可以超激大遊行／組自己很喜歡的一隊嗎？
A：可以，等你能把角色練高等和角色齊全再說

Q：請問有沒有一些固定的組隊基本概念？
A：除了上述影片所說的以外，這就要看打法需求了，一般來說，只是正攻的話，就按平常關卡需求上陣角色即可，甚至在其中插入一些聯組改良也是可行的，速攻的話，一般都會綁定初始金額／初始貓炮規格等短期內效用最大的聯組，你要特化暫停妨害也是綁聯組等，這些就看你抄攻略的多寡了，基本上就是看多，懂得自然也會更多

Q：其他不懂的組隊思路問題？
A：歡迎在留言或回應指出，我會直接在文章裡解答
————
本篇完
"
	,"2024-06-07 12:40:31"
    ,null -- reply
    ,0 -- status
    ,3 -- userid
    ,5); -- gameid
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   
VALUES(
	"每個月的2號跟22號，記得拿炸彈貓！(內有取得條件及方法)"
    ,"每個月的2號跟22號，
記得拿（免費）對黑最強控場「炸彈貓」!

取得時間:每月2號跟22號,
「下午14:22~14:24」,
只開放兩分鐘的快打關卡!
記得設個鬧鐘提醒自己一下唷

《取得條件》:
1.先取得花盆貓(因為炸彈是花盆的進階)
2.未來三需通關

我知道這是用火文,
但相信還是很多新朋友不會注意到,
所以發個文提醒一下~♥

至於關卡內容是無難度,
派個牛貓去甩一甩頭就過了；
入手炸彈貓後,很多對黑關都會簡單許多!!
"
	,"2024-06-21 12:40:31"
    ,null -- reply
    ,0 -- status
    ,7 -- userid
    ,5); -- gameid
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   
VALUES(
	"【心得】新手福音---9000萬下載紀念 升級所需XP1/2活動!"
    ,"從昨天開始的9000萬下載紀念活動中除了和以往一樣的下載下載紀念活動外
還多了我玩了3、4年來第一次(沒記錯的話)出現的升級XP1/2活動!
這對拿到很多新貓的新手和想衝等排的很不錯啊!
不過對XP爆棚想消耗掉或是很多貓已經30+又沒有貓眼石的玩家沒幫助就是了~
"
	,"2024-06-11 09:54:31"
    ,null -- reply
    ,0 -- status
    ,7 -- userid
    ,13); -- gameid
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   
VALUES(
	"【問題】請問克洛格果實的檢查方法（曠野之息）"
    ,"    雖然看著地圖一區區的拿了果實
城堡已確認全拿 但目前900還差2個
很不想再看著地圖一個個確認….眼睛好累
    請問有其他方法去尋找剩下的兩個嗎…
感謝
"
	,"2024-06-30 11:04:31"
    ,null -- reply
    ,0 -- status
    ,6 -- userid
    ,9); -- gameid
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   
VALUES(
	"【問題】薩科的分身是否可以代替本體到經驗區吸經驗？"
    ,"對線有時候站那麼遠
    不怕被趕出經驗區 吸不到經驗嗎？
    明明血線掉超快，卻可以反殺？"
	,"2024-07-21 11:04:31"
    ,null -- reply
    ,0 -- status
    ,2 -- userid
    ,10); -- gameid
INSERT INTO art (art_title,art_content,art_timestamp,art_reply,art_status ,art_user_id,art_game_id)   
VALUES(
	"【情報】14.13，法洛士部分技能調整跟其他細項"
    ,"法洛士
基礎攻擊:60 削弱至 57
被動擊殺單位不再提供攻擊速度，現在擊殺敵方單位提供攻擊和法強
擊殺敵方單位提供:3-20攻擊力 和6-30 法術強度 [基於英雄等級]
參與擊殺英雄提供:15-30攻擊力和 25-50法術強度 [基於英雄等級]
效果持續時間和先前一樣[5/7/9/11秒 於 1/6/11/16級提升]



W被動額外傷害:7/13/19/25/31 加強至 7/14/21/28/35
W觸發每層效果所返還的基礎技能冷卻時間:12%加強至 15% [3層36% -> 45%]
W+Q額外已損失生命值傷害:6/8/10/12/14% 削弱至 6/7/8/9/10%,
W+Q額外已損失生命值傷害現在會隨W技能等級增加而不是英雄等級 [1/4/7/10/13級]


符文
電刑:
冷卻時間:25-20秒 加強至 全等級20秒
基礎傷害:30-220 → 50-190


靈魂收割
基傷:20-80 削弱至 全等級20
每收集一個靈魂增加的傷害:5 加強至 8
冷卻時間:45秒 加強至 30秒



其他:
1.新pve模式預計14.14版本上線(7/19號)
2.石頭人模組更新(2025年)
3.新英雄Ambessa是戰士。但是操作難度屬於高，不知道有沒有跟亞菲利歐一樣難。
4.Ray Yonggi(就是個設計師):已針對斯溫進行改動，改動程度約莫於庫奇跟雷柯薩那樣的規模。同時納菲芮、希格斯、卡桑蒂目前都在觀察名單中。觀察而已沒說要馬上動"
	,"2024-07-21 11:04:31"
    ,null -- reply
    ,0 -- status
    ,7 -- userid
    ,3); -- gameid



-- favor table -----------------------
CREATE TABLE favor (
	favor_id INT AUTO_INCREMENT NOT NULL,
    favor_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    favor_status INT,
    favor_art_id INT,
    favor_user_id INT,
    -- CONSTRAINT  favor_art_id FOREIGN KEY (favor_art_id) REFERENCES art (art_id),
    -- CONSTRAINT  favor_user_id FOREIGN KEY (favor_user_id) REFERENCES user (user_id),
	CONSTRAINT favor_primary_key PRIMARY KEY (favor_id)
)AUTO_INCREMENT = 1;


-- message table ---------------------------------
CREATE TABLE message (
	message_id INT AUTO_INCREMENT NOT NULL,
    message_content mediumtext,
    message_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    message_art_id INT,
    message_user_id INT,
    CONSTRAINT  message_art_id FOREIGN KEY (message_art_id) REFERENCES art (art_id),
    CONSTRAINT  message_user_id FOREIGN KEY (message_user_id) REFERENCES user (user_id),
	CONSTRAINT message_primary_key PRIMARY KEY (message_id)
)AUTO_INCREMENT = 1;

INSERT INTO message (message_content,message_timestamp, message_art_id,message_user_id)  
	VALUES ("建議發影片","2024-06-05 23:13:43 ",1,2);
INSERT INTO message (message_content,message_timestamp, message_art_id,message_user_id)  
	VALUES ("影片只能錄全部發上來嗎","2024-06-05 23:15:43",1,1);
INSERT INTO message (message_content,message_timestamp, message_art_id,message_user_id)  
	VALUES ("這回老子也深有同感，看到羊頭就來氣","2024-06-05 13:28:56 ",2,4);
INSERT INTO message (message_content,message_timestamp, message_art_id,message_user_id)  
	VALUES ("R粉快出來叫啊","2024-06-03 16:15:43",2,6);
INSERT INTO message (message_content,message_timestamp, message_art_id,message_user_id)  
	VALUES ("說不定現在內建挖礦程式，看有沒有哪位大神能拆包解惑","2024-06-04  17:40:30",2,8);
INSERT INTO message (message_content,message_timestamp, message_art_id,message_user_id)  
	VALUES ("以前CPU不用多好遊戲順順跑，不知道為啥現在這麼卡","2024-06-02  13:20:30",4,3);
INSERT INTO message (message_content,message_timestamp, message_art_id,message_user_id)  
	VALUES ("我現在可能要等這破活動結束才能玩lol 每次開遊戲都直接重新開機 救我
","2024-06-05  16:23:30",4,9);






