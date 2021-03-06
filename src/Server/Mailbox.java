package Server;

public class Mailbox {
	private String content;

	public Mailbox() {
	}

	public synchronized void setContent(String s) {
		while (content != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		content = s;
		notifyAll();
	}

	public synchronized String getContent() {
		while (content == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String temp = content;
		content = null;
		notifyAll();
		return temp;
	}
}