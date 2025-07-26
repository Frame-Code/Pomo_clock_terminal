package dto;

import com.google.gson.annotations.SerializedName;

public class QuoteResponse {
        @SerializedName("q")
        private String quote;
        @SerializedName("a")
        private String author;
        @SerializedName("h")
        private String html;

        public String getQuote() {
                return quote;
        }

        public void setQuote(String quote) {
                this.quote = quote;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public String getHtml() {
                return html;
        }

        public void setHtml(String html) {
                this.html = html;
        }
}
