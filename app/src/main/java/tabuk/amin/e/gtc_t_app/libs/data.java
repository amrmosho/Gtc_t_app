package tabuk.amin.e.gtc_t_app.libs;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by ismail on 11/18/2015.
 */
public class data {
    Context activity;

    public data(Context contex) {
        this.activity = contex;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("error", "Directory not created");
        }
        return file;
    }


    File saveToExternalStorage(String url, String filename) {
        File file = new File(activity.getExternalFilesDir(null), filename);
        try {



            InputStream is = (InputStream) new URL(url + filename).getContent();
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[2048];

            int length;

            while ((length = is.read(data)) != -1) {
                os.write(data, 0, length);
            }

            is.close();
            os.close();


        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing " + file, e);
        }

return file;

    }


    boolean hasExternalStoragePrivateFile(String filename) {

        File file = new File(activity.getExternalFilesDir(null), filename);
        if (file != null) {
            return file.exists();
        }
        return false;
    }


    void deleteExternalStoragePrivateFile(String filename) {

        File f = new File(activity.getExternalFilesDir(null), filename);
        if (f != null) {
            f.delete();
        }
    }

    File getExternalStoragePrivateFile(String filename) {

        File f = null;

        if (this.hasExternalStoragePrivateFile(filename)) {

            f = new File(activity.getExternalFilesDir(null), filename);

        }


        return f;

    }


}



