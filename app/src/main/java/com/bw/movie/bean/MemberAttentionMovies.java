package com.bw.movie.bean;

import java.util.List;

public class MemberAttentionMovies {


    /**
     * result : [{"fare":0,"id":14,"imageUrl":"http://172.17.8.100/images/movie/stills/jmyx/jmyx1.jpg","name":"解码游戏","releaseTime":1535904000000,"summary":"电玩竞技界大神李浩铭（韩庚 饰）看似是一名宅男程序员，真实身份却是全球排位第一的天才极客\u201c海盗船长\u201d，终于有一天，他享受的平静生活被意外打破，浩铭遭到代号\u201c斑马\u201d的特工乔飞（凤小岳 饰）及其搭档苏仪（李媛 饰）胁迫，要求他加入组织并主力破解号称全球最安全的\u201c绿洲\u201d系统， 此时警方也联系到浩铭，称一直暗中与恐怖组织交易的神秘富豪闫岳（山下智久 饰）宣布将收购\u201c绿洲\u201d，如果成功，后果不堪设想！浩铭发现自己似乎陷入了一个惊天阴谋之中，危急关头他决定跟各方势力玩一场扭转乾坤的解码游戏......"},{"fare":0,"id":4,"imageUrl":"http://172.17.8.100/images/movie/stills/drjzsdtw/drjzsdtw1.jpg","name":"狄仁杰之四大天王","releaseTime":1535299200000,"summary":"狄仁杰(赵又廷 饰）大破神都龙王案，获御赐亢龙锏，并掌管大理寺，使他成为武则天（刘嘉玲 饰）走向权力之路最大的威胁。武则天为了消灭眼中钉，命令尉迟真金（冯绍峰 饰）集结实力强劲的\u201c异人组\u201d，妄图夺取亢龙锏。在医官沙陀忠（林更新 饰）的协助下，狄仁杰既要守护亢龙锏，又要破获神秘奇案，还要面对武则天的步步紧逼，大唐江山陷入了空前的危机之中\u2026\u2026"},{"fare":0,"id":2,"imageUrl":"http://172.17.8.100/images/movie/stills/mtyj/mtyj1.jpg","name":"摩天营救","releaseTime":1532016000000,"summary":"在香港市中心，世界上最高、结构最复杂的摩天大楼遭到破坏，危机一触即发。威尔·索耶（道恩·强森 饰）的妻子萨拉（内芙·坎贝尔 饰）和孩子们在98层被劫为人质，直接暴露在火线上。威尔，这位战争英雄、前联邦调查局救援队员，作为大楼的安保顾问，却被诬陷纵火和谋杀。他必须奋力营救家人，为自己洗脱罪名，关乎生死存亡的高空任务就此展开。"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * fare : 0
         * id : 14
         * imageUrl : http://172.17.8.100/images/movie/stills/jmyx/jmyx1.jpg
         * name : 解码游戏
         * releaseTime : 1535904000000
         * summary : 电玩竞技界大神李浩铭（韩庚 饰）看似是一名宅男程序员，真实身份却是全球排位第一的天才极客“海盗船长”，终于有一天，他享受的平静生活被意外打破，浩铭遭到代号“斑马”的特工乔飞（凤小岳 饰）及其搭档苏仪（李媛 饰）胁迫，要求他加入组织并主力破解号称全球最安全的“绿洲”系统， 此时警方也联系到浩铭，称一直暗中与恐怖组织交易的神秘富豪闫岳（山下智久 饰）宣布将收购“绿洲”，如果成功，后果不堪设想！浩铭发现自己似乎陷入了一个惊天阴谋之中，危急关头他决定跟各方势力玩一场扭转乾坤的解码游戏......
         */

        private int fare;
        private int id;
        private String imageUrl;
        private String name;
        private long releaseTime;
        private String summary;

        public int getFare() {
            return fare;
        }

        public void setFare(int fare) {
            this.fare = fare;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
