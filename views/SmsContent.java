  /**
   * 通过短信数据库获取短信内容， 自动后去短信验证码
   */
public class SmsContent extends ContentObserver {
	   public SmsContent(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            getSmsFromPhone();
        }

        private void getSmsFromPhone() {
        ContentResolver contentResolver = getActivity().getContentResolver();
        String[] projection = new String[]{"body"};
        String where = "date >" + (System.currentTimeMillis() - 30000);
        Cursor cursor = contentResolver.query(Uri.parse(SMS_CONTENT), projection, where, null, "date desc");

        if (cursor == null) {
            return;
        }
        if (cursor.moveToNext()) {
            String smsBody = cursor.getString(cursor.getColumnIndex("body"));
            String verificationCode = getDynaicPassword(smsBody);
            showToast("verifi_code: " + verificationCode);
            mEditTextVericationCode.setText(verificationCode);        //自动填充验证码
        }
        if (Build.VERSION.SDK_INT < 14) {
            cursor.close();
        }
    }

    /***
     * 自动获取短信中的连续6位的数字验证码
     *
     * @param str 短信内容
     * @return 验证码
     */
    public static String getDynaicPassword(String str) {
        Pattern continuousNumberPattern = Pattern.compile("[0-9\\.]+");
        Matcher matcher = continuousNumberPattern.matcher(str);
        String dynamicPassword = "";
        while (matcher.find()) {
            if (matcher.group().length() == 6) {
                dynamicPassword = matcher.group();
            }
        }
        return dynamicPassword;
    }

}