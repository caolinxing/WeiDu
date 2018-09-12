package com.bw.movie.bean;

import java.util.List;

public class MovieScheduleBean {

    /**
     * result : [{"beginTime":"14:35","duration":"99分钟","endTime":"16:12","id":65,"screeningHall":"9号厅","seatsTotal":82,"seatsUseCount":36,"status":2},{"beginTime":"15:35","duration":"99分钟","endTime":"17:12","id":144,"screeningHall":"10号厅","seatsTotal":82,"seatsUseCount":36,"status":2},{"beginTime":"15:00","duration":"99分钟","endTime":"16:35","id":145,"screeningHall":"8号厅","seatsTotal":82,"seatsUseCount":36,"status":2},{"beginTime":"15:10","duration":"99分钟","endTime":"16:45","id":146,"screeningHall":"7号厅","seatsTotal":82,"seatsUseCount":36,"status":2},{"beginTime":"15:10","duration":"99分钟","endTime":"16:45","id":147,"screeningHall":"1号厅","seatsTotal":82,"seatsUseCount":36,"status":2}]
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
         * beginTime : 14:35
         * duration : 99分钟
         * endTime : 16:12
         * id : 65
         * screeningHall : 9号厅
         * seatsTotal : 82
         * seatsUseCount : 36
         * status : 2
         */

        private String beginTime;
        private String duration;
        private String endTime;
        private int id;
        private String screeningHall;
        private int seatsTotal;
        private int seatsUseCount;
        private int status;

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }

        public int getSeatsTotal() {
            return seatsTotal;
        }

        public void setSeatsTotal(int seatsTotal) {
            this.seatsTotal = seatsTotal;
        }

        public int getSeatsUseCount() {
            return seatsUseCount;
        }

        public void setSeatsUseCount(int seatsUseCount) {
            this.seatsUseCount = seatsUseCount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
