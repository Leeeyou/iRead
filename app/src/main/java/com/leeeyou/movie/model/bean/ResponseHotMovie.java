package com.leeeyou.movie.model.bean;

import com.google.gson.Gson;

import java.util.List;

public class ResponseHotMovie {


    /**
     * count : 20
     * start : 0
     * total : 40
     * subjects : [{"rating":{"max":10,"average":7.3,"stars":"40","min":0},"genres":["动作","科幻","冒险"],"title":"大黄蜂","casts":[{"alt":"https://movie.douban.com/celebrity/1312964/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg"},"name":"海莉·斯坦菲尔德","id":"1312964"},{"alt":"https://movie.douban.com/celebrity/1376970/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1545624925.39.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1545624925.39.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1545624925.39.jpg"},"name":"小豪尔赫·兰登伯格","id":"1376970"},{"alt":"https://movie.douban.com/celebrity/1044883/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p23477.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p23477.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p23477.jpg"},"name":"约翰·塞纳","id":"1044883"}],"collect_count":131139,"original_title":"Bumblebee","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1305796/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg"},"name":"特拉维斯·奈特","id":"1305796"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541662397.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541662397.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541662397.jpg"},"alt":"https://movie.douban.com/subject/26394152/","id":"26394152"},{"rating":{"max":10,"average":7,"stars":"35","min":0},"genres":["动作","犯罪"],"title":"\u201c大\u201d人物","casts":[{"alt":"https://movie.douban.com/celebrity/1314827/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1445948736.67.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1445948736.67.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1445948736.67.jpg"},"name":"王千源","id":"1314827"},{"alt":"https://movie.douban.com/celebrity/1315866/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1545816620.37.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1545816620.37.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1545816620.37.jpg"},"name":"包贝尔","id":"1315866"},{"alt":"https://movie.douban.com/celebrity/1317139/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1371453539.51.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1371453539.51.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1371453539.51.jpg"},"name":"王迅","id":"1317139"}],"collect_count":4577,"original_title":"\u201c大\u201d人物","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1327592/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1505707565.9.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1505707565.9.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1505707565.9.jpg"},"name":"五百","id":"1327592"}],"year":"2019","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2544988187.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2544988187.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2544988187.jpg"},"alt":"https://movie.douban.com/subject/26816076/","id":"26816076"},{"rating":{"max":10,"average":6,"stars":"30","min":0},"genres":["剧情","喜剧"],"title":"来电狂响","casts":[{"alt":"https://movie.douban.com/celebrity/1009179/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.jpg"},"name":"佟大为","id":"1009179"},{"alt":"https://movie.douban.com/celebrity/1319032/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1444800807.11.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1444800807.11.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1444800807.11.jpg"},"name":"马丽","id":"1319032"},{"alt":"https://movie.douban.com/celebrity/1000145/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2520.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2520.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2520.jpg"},"name":"霍思燕","id":"1000145"}],"collect_count":92456,"original_title":"来电狂响","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1321152/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1483685290.54.jpg"},"name":"于淼","id":"1321152"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2542268337.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2542268337.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2542268337.jpg"},"alt":"https://movie.douban.com/subject/30377703/","id":"30377703"},{"rating":{"max":10,"average":7.8,"stars":"40","min":0},"genres":["动作","奇幻","冒险"],"title":"海王","casts":[{"alt":"https://movie.douban.com/celebrity/1022614/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32853.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32853.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32853.jpg"},"name":"杰森·莫玛","id":"1022614"},{"alt":"https://movie.douban.com/celebrity/1044702/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34697.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34697.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34697.jpg"},"name":"艾梅柏·希尔德","id":"1044702"},{"alt":"https://movie.douban.com/celebrity/1010539/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9206.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9206.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9206.jpg"},"name":"威廉·达福","id":"1010539"}],"collect_count":665186,"original_title":"Aquaman","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1032122/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1509950363.8.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1509950363.8.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1509950363.8.jpg"},"name":"温子仁","id":"1032122"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541280047.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541280047.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541280047.jpg"},"alt":"https://movie.douban.com/subject/3878007/","id":"3878007"},{"rating":{"max":10,"average":8.7,"stars":"45","min":0},"genres":["动作","科幻","动画"],"title":"蜘蛛侠：平行宇宙","casts":[{"alt":"https://movie.douban.com/celebrity/1350106/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1434437756.07.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1434437756.07.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1434437756.07.jpg"},"name":"沙梅克·摩尔","id":"1350106"},{"alt":"https://movie.douban.com/celebrity/1316713/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1449582908.84.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1449582908.84.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1449582908.84.jpg"},"name":"杰克·约翰逊","id":"1316713"},{"alt":"https://movie.douban.com/celebrity/1312964/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg"},"name":"海莉·斯坦菲尔德","id":"1312964"}],"collect_count":259110,"original_title":"Spider-Man: Into the Spider-Verse","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1310107/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1519064730.28.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1519064730.28.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1519064730.28.jpg"},"name":"鲍勃·佩尔西凯蒂","id":"1310107"},{"alt":"https://movie.douban.com/celebrity/1324415/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p59042.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p59042.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p59042.jpg"},"name":"彼得·拉姆齐","id":"1324415"},{"alt":"https://movie.douban.com/celebrity/1296189/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543307159.85.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543307159.85.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543307159.85.jpg"},"name":"罗德尼·罗斯曼","id":"1296189"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542867516.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542867516.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542867516.jpg"},"alt":"https://movie.douban.com/subject/26374197/","id":"26374197"},{"rating":{"max":10,"average":8.9,"stars":"45","min":0},"genres":["纪录片","家庭"],"title":"四个春天","casts":[{"alt":"https://movie.douban.com/celebrity/1405092/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542942707.25.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542942707.25.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542942707.25.jpg"},"name":"陆运坤","id":"1405092"},{"alt":"https://movie.douban.com/celebrity/1405093/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542942765.23.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542942765.23.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542942765.23.jpg"},"name":"李桂贤","id":"1405093"}],"collect_count":38439,"original_title":"四个春天","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1386569/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533613477.28.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533613477.28.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533613477.28.jpg"},"name":"陆庆屹","id":"1386569"}],"year":"2017","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2540578887.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2540578887.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2540578887.jpg"},"alt":"https://movie.douban.com/subject/27191492/","id":"27191492"},{"rating":{"max":10,"average":0,"stars":"00","min":0},"genres":["爱情","动画","奇幻"],"title":"白蛇：缘起","casts":[{"alt":"https://movie.douban.com/celebrity/1389748/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520590413.89.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520590413.89.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520590413.89.jpg"},"name":"张喆","id":"1389748"},{"alt":"https://movie.douban.com/celebrity/1341265/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1439904943.23.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1439904943.23.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1439904943.23.jpg"},"name":"杨天翔","id":"1341265"},{"alt":"https://movie.douban.com/celebrity/1340810/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1501640829.72.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1501640829.72.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1501640829.72.jpg"},"name":"唐小喜","id":"1340810"}],"collect_count":1920,"original_title":"白蛇：缘起","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1401171/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1537807069.66.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1537807069.66.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1537807069.66.jpg"},"name":"黄家康","id":"1401171"},{"alt":"https://movie.douban.com/celebrity/1401175/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1537807088.34.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1537807088.34.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1537807088.34.jpg"},"name":"赵霁","id":"1401175"}],"year":"2019","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544313786.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544313786.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544313786.jpg"},"alt":"https://movie.douban.com/subject/30331149/","id":"30331149"},{"rating":{"max":10,"average":7.4,"stars":"40","min":0},"genres":["剧情","运动"],"title":"奎迪：英雄再起","casts":[{"alt":"https://movie.douban.com/celebrity/1107320/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1473330747.67.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1473330747.67.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1473330747.67.jpg"},"name":"迈克尔·B·乔丹","id":"1107320"},{"alt":"https://movie.douban.com/celebrity/1047996/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p262.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p262.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p262.jpg"},"name":"西尔维斯特·史泰龙","id":"1047996"},{"alt":"https://movie.douban.com/celebrity/1027395/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1509782172.11.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1509782172.11.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1509782172.11.jpg"},"name":"泰莎·汤普森","id":"1027395"}],"collect_count":10422,"original_title":"Creed II","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1391361/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1538121045.58.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1538121045.58.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1538121045.58.jpg"},"name":"小斯蒂芬·卡普尔","id":"1391361"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544510053.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544510053.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544510053.jpg"},"alt":"https://movie.douban.com/subject/26707088/","id":"26707088"},{"rating":{"max":10,"average":6,"stars":"30","min":0},"genres":["喜剧","动作","冒险"],"title":"印度暴徒","casts":[{"alt":"https://movie.douban.com/celebrity/1031931/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13628.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13628.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13628.jpg"},"name":"阿米尔·汗","id":"1031931"},{"alt":"https://movie.douban.com/celebrity/1010569/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p53718.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p53718.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p53718.jpg"},"name":"卡特莉娜·卡芙","id":"1010569"},{"alt":"https://movie.douban.com/celebrity/1027845/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9190.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9190.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9190.jpg"},"name":"阿米达普·巴强","id":"1027845"}],"collect_count":10681,"original_title":"Thugs of Hindostan","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1308426/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1495324131.21.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1495324131.21.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1495324131.21.jpg"},"name":"维贾伊·克利须那·阿查里雅","id":"1308426"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541754700.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541754700.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541754700.jpg"},"alt":"https://movie.douban.com/subject/27019982/","id":"27019982"},{"rating":{"max":10,"average":9.1,"stars":"45","min":0},"genres":["动画","奇幻","冒险"],"title":"龙猫","casts":[{"alt":"https://movie.douban.com/celebrity/1019382/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1455201170.02.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1455201170.02.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1455201170.02.jpg"},"name":"日高法子","id":"1019382"},{"alt":"https://movie.douban.com/celebrity/1025582/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p29537.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p29537.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p29537.jpg"},"name":"坂本千夏","id":"1025582"},{"alt":"https://movie.douban.com/celebrity/1379738/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503457262.72.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503457262.72.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503457262.72.jpg"},"name":"糸井重里","id":"1379738"}],"collect_count":844146,"original_title":"となりのトトロ","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1054439/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p616.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p616.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p616.jpg"},"name":"宫崎骏","id":"1054439"}],"year":"1988","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2540924496.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2540924496.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2540924496.jpg"},"alt":"https://movie.douban.com/subject/1291560/","id":"1291560"},{"rating":{"max":10,"average":3.5,"stars":"20","min":0},"genres":["动作","奇幻","冒险"],"title":"云南虫谷","casts":[{"alt":"https://movie.douban.com/celebrity/1318565/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543907920.47.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543907920.47.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543907920.47.jpg"},"name":"蔡珩","id":"1318565"},{"alt":"https://movie.douban.com/celebrity/1316959/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1538706988.45.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1538706988.45.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1538706988.45.jpg"},"name":"顾璇","id":"1316959"},{"alt":"https://movie.douban.com/celebrity/1363813/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543907688.08.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543907688.08.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543907688.08.jpg"},"name":"于恒","id":"1363813"}],"collect_count":20990,"original_title":"云南虫谷","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1320824/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543204019.02.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543204019.02.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1543204019.02.jpg"},"name":"非行","id":"1320824"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542212636.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542212636.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542212636.jpg"},"alt":"https://movie.douban.com/subject/26744597/","id":"26744597"},{"rating":{"max":10,"average":7,"stars":"35","min":0},"genres":["剧情","爱情","悬疑"],"title":"地球最后的夜晚","casts":[{"alt":"https://movie.douban.com/celebrity/1025141/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1507363720.81.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1507363720.81.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1507363720.81.jpg"},"name":"汤唯","id":"1025141"},{"alt":"https://movie.douban.com/celebrity/1275273/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534406418.87.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534406418.87.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534406418.87.jpg"},"name":"黄觉","id":"1275273"},{"alt":"https://movie.douban.com/celebrity/1012646/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1494561948.78.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1494561948.78.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1494561948.78.jpg"},"name":"张艾嘉","id":"1012646"}],"collect_count":135183,"original_title":"地球最后的夜晚","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1324480/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447134443.47.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447134443.47.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1447134443.47.jpg"},"name":"毕赣","id":"1324480"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541183610.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541183610.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541183610.jpg"},"alt":"https://movie.douban.com/subject/26633257/","id":"26633257"},{"rating":{"max":10,"average":2.6,"stars":"15","min":0},"genres":["喜剧","冒险"],"title":"断片之险途夺宝","casts":[{"alt":"https://movie.douban.com/celebrity/1000905/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46.jpg"},"name":"葛优","id":"1000905"},{"alt":"https://movie.douban.com/celebrity/1317663/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p40756.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p40756.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p40756.jpg"},"name":"岳云鹏","id":"1317663"},{"alt":"https://movie.douban.com/celebrity/1016663/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1536658358.18.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1536658358.18.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1536658358.18.jpg"},"name":"杜淳","id":"1016663"}],"collect_count":8367,"original_title":"断片之险途夺宝","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1324612/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pbYO4zDByocwcel_avatar_uploaded1352024636.33.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pbYO4zDByocwcel_avatar_uploaded1352024636.33.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pbYO4zDByocwcel_avatar_uploaded1352024636.33.jpg"},"name":"罗登","id":"1324612"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541553709.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541553709.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541553709.jpg"},"alt":"https://movie.douban.com/subject/26882457/","id":"26882457"},{"rating":{"max":10,"average":0,"stars":"00","min":0},"genres":["犯罪","悬疑"],"title":"沉默的雪","casts":[{"alt":"https://movie.douban.com/celebrity/1400479/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1536388455.52.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1536388455.52.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1536388455.52.jpg"},"name":"任宇","id":"1400479"},{"alt":"https://movie.douban.com/celebrity/1365297/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506857301.82.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506857301.82.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506857301.82.jpg"},"name":"陈绿","id":"1365297"},{"alt":"https://movie.douban.com/celebrity/1406635/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544683064.31.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544683064.31.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544683064.31.jpg"},"name":"王笙","id":"1406635"}],"collect_count":188,"original_title":"沉默的雪","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1406634/","avatars":{"small":"https://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"https://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"https://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"风剑","id":"1406634"}],"year":"2019","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2543163892.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2543163892.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2543163892.jpg"},"alt":"https://movie.douban.com/subject/30402144/","id":"30402144"},{"rating":{"max":10,"average":6.5,"stars":"35","min":0},"genres":["喜剧","动画","奇幻"],"title":"绿毛怪格林奇","casts":[{"alt":"https://movie.douban.com/celebrity/1009405/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p41072.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p41072.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p41072.jpg"},"name":"本尼迪克特·康伯巴奇","id":"1009405"},{"alt":"https://movie.douban.com/celebrity/1387860/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517222739.45.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517222739.45.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517222739.45.jpg"},"name":"卡梅伦·丝蕾","id":"1387860"},{"alt":"https://movie.douban.com/celebrity/1031815/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32735.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32735.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32735.jpg"},"name":"拉什达·琼斯","id":"1031815"}],"collect_count":6954,"original_title":"The Grinch","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1280591/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1467266335.21.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1467266335.21.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1467266335.21.jpg"},"name":"亚罗·切尼","id":"1280591"},{"alt":"https://movie.douban.com/celebrity/1032291/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34173.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34173.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34173.jpg"},"name":"斯科特·摩西尔","id":"1032291"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2539666559.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2539666559.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2539666559.jpg"},"alt":"https://movie.douban.com/subject/23774869/","id":"23774869"},{"rating":{"max":10,"average":5.9,"stars":"30","min":0},"genres":["剧情","动作"],"title":"叶问外传：张天志","casts":[{"alt":"https://movie.douban.com/celebrity/1318005/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1436716618.28.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1436716618.28.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1436716618.28.jpg"},"name":"张晋","id":"1318005"},{"alt":"https://movie.douban.com/celebrity/1014003/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1493202154.34.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1493202154.34.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1493202154.34.jpg"},"name":"戴夫·巴蒂斯塔","id":"1014003"},{"alt":"https://movie.douban.com/celebrity/1312846/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p39129.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p39129.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p39129.jpg"},"name":"柳岩","id":"1312846"}],"collect_count":11674,"original_title":"葉問外傳：張天志","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1275026/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9332.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9332.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9332.jpg"},"name":"袁和平","id":"1275026"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542380253.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542380253.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542380253.jpg"},"alt":"https://movie.douban.com/subject/26796664/","id":"26796664"},{"rating":{"max":10,"average":8.1,"stars":"40","min":0},"genres":["剧情","喜剧"],"title":"无名之辈","casts":[{"alt":"https://movie.douban.com/celebrity/1274626/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1415455964.31.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1415455964.31.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1415455964.31.jpg"},"name":"陈建斌","id":"1274626"},{"alt":"https://movie.douban.com/celebrity/1362973/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1478066140.77.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1478066140.77.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1478066140.77.jpg"},"name":"任素汐","id":"1362973"},{"alt":"https://movie.douban.com/celebrity/1316365/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1541855083.14.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1541855083.14.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1541855083.14.jpg"},"name":"潘斌龙","id":"1316365"}],"collect_count":600078,"original_title":"无名之辈","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1326752/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1541992522.36.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1541992522.36.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1541992522.36.jpg"},"name":"饶晓志","id":"1326752"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2539661066.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2539661066.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2539661066.jpg"},"alt":"https://movie.douban.com/subject/27110296/","id":"27110296"},{"rating":{"max":10,"average":4,"stars":"20","min":0},"genres":["喜剧","奇幻"],"title":"天气预爆","casts":[{"alt":"https://movie.douban.com/celebrity/1274979/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1518431956.11.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1518431956.11.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1518431956.11.jpg"},"name":"肖央","id":"1274979"},{"alt":"https://movie.douban.com/celebrity/1323516/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1368850348.93.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1368850348.93.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1368850348.93.jpg"},"name":"杜鹃","id":"1323516"},{"alt":"https://movie.douban.com/celebrity/1327084/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1363597076.12.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1363597076.12.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1363597076.12.jpg"},"name":"常远","id":"1327084"}],"collect_count":21304,"original_title":"天气预爆","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1274979/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1518431956.11.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1518431956.11.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1518431956.11.jpg"},"name":"肖央","id":"1274979"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2543353290.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2543353290.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2543353290.jpg"},"alt":"https://movie.douban.com/subject/26994789/","id":"26994789"},{"rating":{"max":10,"average":6.9,"stars":"35","min":0},"genres":["剧情"],"title":"神探狗笨吉","casts":[{"alt":"https://movie.douban.com/celebrity/1358708/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pkgttz5tui54cel_avatar_uploaded1464968611.11.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pkgttz5tui54cel_avatar_uploaded1464968611.11.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pkgttz5tui54cel_avatar_uploaded1464968611.11.jpg"},"name":"加布里埃尔·贝特曼","id":"1358708"},{"alt":"https://movie.douban.com/celebrity/1369479/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1488088653.09.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1488088653.09.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1488088653.09.jpg"},"name":"达比·坎普","id":"1369479"},{"alt":"https://movie.douban.com/celebrity/1027256/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p19702.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p19702.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p19702.jpg"},"name":"基拉·桑切斯","id":"1027256"}],"collect_count":2417,"original_title":"Benji","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1369948/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1522748236.29.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1522748236.29.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1522748236.29.jpg"},"name":"布兰登·坎普","id":"1369948"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541725145.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541725145.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541725145.jpg"},"alt":"https://movie.douban.com/subject/26895438/","id":"26895438"},{"rating":{"max":10,"average":0,"stars":"00","min":0},"genres":["剧情"],"title":"大微商","casts":[{"alt":"https://movie.douban.com/celebrity/1406780/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544880033.03.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544880033.03.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544880033.03.jpg"},"name":"刘东浒","id":"1406780"},{"alt":"https://movie.douban.com/celebrity/1313238/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p15699.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p15699.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p15699.jpg"},"name":"程媛媛","id":"1313238"},{"alt":"https://movie.douban.com/celebrity/1037662/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1357290860.44.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1357290860.44.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1357290860.44.jpg"},"name":"李子雄","id":"1037662"}],"collect_count":385,"original_title":"大微商","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1406781/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544880983.71.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544880983.71.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544880983.71.jpg"},"name":"李锐","id":"1406781"}],"year":"2019","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544995150.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544995150.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544995150.jpg"},"alt":"https://movie.douban.com/subject/30400471/","id":"30400471"}]
     * title : 正在上映的电影-北京
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean {
        /**
         * rating : {"max":10,"average":7.3,"stars":"40","min":0}
         * genres : ["动作","科幻","冒险"]
         * title : 大黄蜂
         * casts : [{"alt":"https://movie.douban.com/celebrity/1312964/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg"},"name":"海莉·斯坦菲尔德","id":"1312964"},{"alt":"https://movie.douban.com/celebrity/1376970/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1545624925.39.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1545624925.39.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1545624925.39.jpg"},"name":"小豪尔赫·兰登伯格","id":"1376970"},{"alt":"https://movie.douban.com/celebrity/1044883/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p23477.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p23477.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p23477.jpg"},"name":"约翰·塞纳","id":"1044883"}]
         * collect_count : 131139
         * original_title : Bumblebee
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1305796/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg"},"name":"特拉维斯·奈特","id":"1305796"}]
         * year : 2018
         * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541662397.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541662397.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541662397.jpg"}
         * alt : https://movie.douban.com/subject/26394152/
         * id : 26394152
         */

        private RatingBean rating;
        private String title;
        private int collect_count;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesBean images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsBean> casts;
        private List<DirectorsBean> directors;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public static class RatingBean {
            /**
             * max : 10
             * average : 7.3
             * stars : 40
             * min : 0
             */

            private int max;
            private double average;
            private String stars;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541662397.jpg
             * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541662397.jpg
             * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541662397.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1312964/
             * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg"}
             * name : 海莉·斯坦菲尔德
             * id : 1312964
             */

            private String alt;
            private AvatarsBean avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean {
                /**
                 * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg
                 * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg
                 * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20419.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1305796/
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg"}
             * name : 特拉维斯·奈特
             * id : 1305796
             */

            private String alt;
            private AvatarsBeanX avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1471358307.31.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
