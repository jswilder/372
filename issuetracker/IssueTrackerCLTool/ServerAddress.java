//package edu.clemson.cs.cpsc3720.project.client;

import java.io.Serializable;

public class ServerAddress implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1708353186883153123L;
	private String address;
	private int port;

	public ServerAddress(String address, int port) {
		if (port < 0) {
			port = 8080;
		} else {
			this.port = port;
		}

		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
