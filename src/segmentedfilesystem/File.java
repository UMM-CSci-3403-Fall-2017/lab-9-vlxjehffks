package segmentedfilesystem;

import java.io.*;
import java.util.*;

public class File {

	ArrayList<byte[]> files = new ArrayList<>[];

	String FileName = "";

	public void insert(byte[] data){
		files.add(data);
	}

	public void 
