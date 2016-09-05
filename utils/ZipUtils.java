package com.bobo.splayer.downloadframework.filedownload;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {


    private static final int BUFFER_SIZE = 4096;

    private static void extractFile(ZipInputStream in, File destFile) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
        int count = -1;
        while ((count = in.read(buffer)) != -1)
            out.write(buffer, 0, count);
        out.close();
    }

    private static File mkdirs(File outdir, String path) {
        File d = new File(outdir, path);
        if (!d.exists())
            d.mkdirs();

        return d;
    }

    private static String dirpart(String name) {
        int s = name.lastIndexOf(File.separatorChar);
        return s == -1 ? null : name.substring(0, s);
    }

    /***
     * Extract zipfile to outdir with complete directory structure
     *
     * @param zipfile Input .zip file
     * @param outdir  Output directory
     */
    public static File[] extract(File zipfile, File outdir) {
        try {
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipfile));
            ZipEntry entry;
            String name, dir;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                if (entry.isDirectory()) {
                    mkdirs(outdir, name);
                    continue;
                }
                /* this part is necessary because file entry can come before
                * directory entry where is file located
                * i.e.:
                *   /foo/foo.txt
                *   /foo/
                */
                dir = dirpart(name);
                if (dir != null) {
                    mkdirs(outdir, dir);
                }

                File destFile = new File(outdir, name);
                extractFile(zin, destFile);
                Log.i("TAG", "UnZip file===" + name + "  ====>  " + destFile.getAbsolutePath());
            }

            zin.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        LogUtil.i("TAG", "**************UnZip End*************");
        return outdir.listFiles();
    }

    /**
     * 将下载的Zip文件中的File（包括apk文件，数据包)解压到指定的Dir中.
     * @param zipFile       需要解压的ZipFile
     * @param outAppFile     目标app文件->../game_download/xxx  .apk
     * @param outObbDir     数据包Obb所在Dir-> ../Android/obb/
     */
    public static String extract(File zipFile, File outAppFile, File outObbDir) {
        File obbFile = null;
        try {
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry entry;
            String name, dir;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                if (entry.isDirectory()) {
                    mkdirs(outObbDir, name);
                    continue;
                }

                if (name.contains(".apk")) {
                    extractFile(zin, outAppFile);
                    Log.i("TAG", "UnZip file===" + name + "  ====>  " + outAppFile.getAbsolutePath());
                } else if(name.contains(".obb")){
                /*
                * this part is necessary because file entry can come before
                * directory entry where is file located
                * i.e.:
                *   /foo/foo.txt
                *   /foo/
                */
                    dir = dirpart(name);
                    if (dir != null) {
                        mkdirs(outObbDir, dir);
                    }
                    obbFile = new File(outObbDir, name);
                    extractFile(zin, obbFile);
                    Log.i("TAG", "UnZip file===" + name + "  ====>  " + obbFile.getAbsolutePath());
                }
            }

            zin.close();
        } catch (IOException e) {
            LogUtil.e("TAG", "解压出错了！！！");
            obbFile = null;
            e.printStackTrace();
        }
        Log.i("TAG", "**************UnZip End*************");
        return obbFile != null ? obbFile.getAbsolutePath() : null;
    }
}