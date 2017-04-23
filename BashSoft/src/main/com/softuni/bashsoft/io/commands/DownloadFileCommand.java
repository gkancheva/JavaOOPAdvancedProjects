package main.com.softuni.bashsoft.io.commands;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.annotations.Inject;
import main.com.softuni.bashsoft.exceptions.InvalidInputException;
import main.com.softuni.bashsoft.network.DownloadManager;

@Alias("download")
public class DownloadFileCommand extends Command {

    @Inject
    private DownloadManager downloadManager;

    public DownloadFileCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }
        String fileUrl = data[1];
        this.downloadManager.download(fileUrl);
    }
}
