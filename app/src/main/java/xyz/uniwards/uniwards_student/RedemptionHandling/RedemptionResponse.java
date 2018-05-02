package xyz.uniwards.uniwards_student.RedemptionHandling;

/**
 * Created by Umayr on 4/28/2018.
 */

public class RedemptionResponse {
        private String response_message;
        private int coupon_id;
        private String date;
        private int id;
        private int student_id;

        public String GetResponse() { return this.response_message; }
        public void SetResponse(String response_message) { this.response_message = response_message; }


        public long GetCouponID() { return coupon_id; }
        public void SetCouponID(int value) { this.coupon_id = value; }

        public String GetDate() { return date; }
        public void SetDate(String value) { this.date = value; }

        public long GetID() { return id; }
        public void SetID(int value) { this.id = value; }

        public long GetStudentID() { return student_id; }
        public void SetStudentID(int value) { this.student_id = value; }
}
