package main.com.softuni.bashsoft.network;

import main.com.softuni.bashsoft.io.OutputWriter;
import main.com.softuni.bashsoft.static_data.Messages;
import main.com.softuni.bashsoft.static_data.SessionData;
import main.com.softuni.bashsoft.exceptions.InvalidPathException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadManager {

    public void download(String fileUrl) {
        URL url;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;

        try {
            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine(Messages.DOWNLOADING_STARTED);
            }
            url = new URL(fileUrl);
            rbc = Channels.newChannel(url.openStream());
            String fileName = extractNameOfFile(url.toString());
            File file = new File(SessionData.currentPath + "/" + fileName);
            fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            if (Thread.currentThread().getName().equals("main")) {
                OutputWriter.writeMessageOnNewLine(Messages.DOWNLOAD_COMPLETED);
            }
        } catch (IOException e) {
            OutputWriter.displayException(e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (rbc != null) {
                    try {
                        rbc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void downloadOnNewThread(String fileUrl) {
        Thread thread = new Thread(() -> download(fileUrl));
        thread.setDaemon(false);
        OutputWriter.writeMessageOnNewLine(
                String.format(Messages.DOWNLOAD_THREAD_WORKER_STARTED, thread.getId()));
        SessionData.threadPool.add(thread);
        thread.start();
    }

    private String extractNameOfFile(String fileUrl) throws MalformedURLException {
        int indexOfLastSlash = fileUrl.lastIndexOf('/');
        if (indexOfLastSlash == -1) {
            throw new InvalidPathException();
        }

        return fileUrl.substring(indexOfLastSlash + 1);
    }
}
