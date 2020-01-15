package com.zz.test.javafxmvn.jna;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;

public class JnaTool {
	
	//private static  String [] hwnds = new String[1000];
	public static HWND getHWND(String title) {
		if(StringUtils.isEmpty(title)) {
			return new HWND();
		}
		boolean dealFlag = false;
		for(String str : JnaConst.APPOINT_LIST) {
			if(str.equals(title)) {
				dealFlag = true;
			}
		}
		
		if(!dealFlag) {
			return User32.INSTANCE.FindWindow(null, title);
		}
		
		Map<HWND, Object> hwnds = JnaTool.getHwndList();
		
		if(hwnds.isEmpty()) {
			return new HWND();
		}
		
		for(HWND h : hwnds.keySet()) {
			if(hwnds.get(h).toString().startsWith(title)) {
				return h;
			}
		}
		
		return new HWND();
		
		
		
	}
	
	
	
	
	public static Map<HWND, Object> getHwndList() {
		Map<HWND, Object> map = new HashMap<HWND, Object>();
		final User32Me user32 = User32Me.INSTANCE;
		user32.EnumWindows(new WNDENUMPROC() {
			int count = 0;

			@Override
			public boolean callback(HWND hWnd, Pointer arg1) {
				byte[] windowText = new byte[512];
				user32.GetWindowTextA(hWnd, windowText, 512);
				String wText = Native.toString(windowText,"gbk");
				// get rid of this if block if you want all windows regardless of whether
				// or not they have text
				if (wText.isEmpty()) {
					return true;
				}
				System.out.println("Found window with text " + hWnd + ", total " + ++count + " Text: " + wText);
			
				map.put(hWnd, wText);
				return true;
			}
		}, null);
		
		return map;
	}
}
