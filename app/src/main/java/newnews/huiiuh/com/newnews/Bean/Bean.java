package newnews.huiiuh.com.newnews.Bean;

import java.util.List;

/**
 * Created by hp on 2017/8/3.
 */

public class Bean {


    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"fb27588d990b60557ab8f0422aae5e2c","title":"这5种来自农村地里的美味，你见过几个？第3种看了好害羞","date":"2017-08-03 13:39","category":"头条","author_name":"美食美客嗨起来","url":"http://mini.eastday.com/mobile/170803133912274.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170803/20170803_f68b2bb61861dc28cc49ee3c900a0e50_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170803/20170803_82c97bc556cac82c6aa461471c3a88fb_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170803/20170803_3b0deddea83360bd50a2464c08263ec1_cover_mwpm_03200403.jpeg"},{"uniquekey":"23ea0a5f1cc8594f7fa730db29e01473","title":"绯闻接二连三 吕秀莲警告：民进党不要变成\u201c桃花党\u201d","date":"2017-08-03 14:04","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170803140425882.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170803/20170803140425_2d17b4baa4b89be8b749d98b7c42b132_1_mwpm_03200403.jpg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"fb27588d990b60557ab8f0422aae5e2c","title":"这5种来自农村地里的美味，你见过几个？第3种看了好害羞","date":"2017-08-03 13:39","category":"头条","author_name":"美食美客嗨起来","url":"http://mini.eastday.com/mobile/170803133912274.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170803/20170803_f68b2bb61861dc28cc49ee3c900a0e50_cover_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170803/20170803_82c97bc556cac82c6aa461471c3a88fb_cover_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170803/20170803_3b0deddea83360bd50a2464c08263ec1_cover_mwpm_03200403.jpeg"},{"uniquekey":"23ea0a5f1cc8594f7fa730db29e01473","title":"绯闻接二连三 吕秀莲警告：民进党不要变成\u201c桃花党\u201d","date":"2017-08-03 14:04","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170803140425882.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170803/20170803140425_2d17b4baa4b89be8b749d98b7c42b132_1_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : fb27588d990b60557ab8f0422aae5e2c
             * title : 这5种来自农村地里的美味，你见过几个？第3种看了好害羞
             * date : 2017-08-03 13:39
             * category : 头条
             * author_name : 美食美客嗨起来
             * url : http://mini.eastday.com/mobile/170803133912274.html
             * thumbnail_pic_s : http://03.imgmini.eastday.com/mobile/20170803/20170803_f68b2bb61861dc28cc49ee3c900a0e50_cover_mwpm_03200403.jpeg
             * thumbnail_pic_s02 : http://03.imgmini.eastday.com/mobile/20170803/20170803_82c97bc556cac82c6aa461471c3a88fb_cover_mwpm_03200403.jpeg
             * thumbnail_pic_s03 : http://03.imgmini.eastday.com/mobile/20170803/20170803_3b0deddea83360bd50a2464c08263ec1_cover_mwpm_03200403.jpeg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}