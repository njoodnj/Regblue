package com.example.bashayer93.regblue;




public class visitorphp extends visitorchanges {
    String URL = "http://10.197.101.229/ES/visitor.php";
    String url = "";
    String response = "";

    public String tampilBiodata() {
        try {
            url = URL + "?operation=view";
            System.out.println("URL Tampil visitorphp: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String inserBiodata(String name, String refnum , String number) {
        try {

            name = name.replace(" ", "%20");
            refnum = refnum.replace(" ", "%20");
            number = number.replace(" ", "%20");

            url = URL + "?operation=insert&name =" + name + "&Refnum=" + refnum + "&Number=" + number;
            System.out.println("URL Insert visitorphp : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getBiodataById(int rid) {
        try {
            url = URL + "?operation=get_biodata_by_id&rid=" + rid;
            System.out.println("URL Insert visitorphp : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    /*public String updateBiodata(String rid, String name, String refnum, String number) {
        try {

            name = name.replace(" ", "%20");
            refnum = refnum.replace(" ", "%20");
            number = number.replace(" ", "%20");

            url = URL + "?operation=update&rid=" + rid + "&name=" + name + "&Refnum=" + refnum + "&Number=" + number;
            System.out.println("URL Insert visitorphp : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }*/

    public String deleteBiodata(int rid) {
        try {
            url = URL + "?operation=delete&rid=" + rid;
            System.out.println("URL Insert visitorphp : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }
}


