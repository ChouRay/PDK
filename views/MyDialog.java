/**
     * 自定义dialog
     */
    private void showDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_change_avatar, null, true);
        dialog = new AlertDialog.Builder(this).create();
        Window dialogWindow = dialog.getWindow();

        WindowManager windowManager = this.getWindowManager();
        WindowManager.LayoutParams wm_layoutParams = dialogWindow.getAttributes(); // 获取对话框当前的参数值

        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        wm_layoutParams.width = screenWidth;

        wm_layoutParams.gravity = Gravity.BOTTOM;

        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);    //设置全屏，默认是有边距的

        dialogWindow.setAttributes(wm_layoutParams);
        dialog.show();
        dialog.getWindow().setContentView(view);
        setChangeAvatarView(view);
    }