package com.zz.test.javafxmvn.jna;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class TestJna {

	public static void main(String[] args) {
		System.out.println(JnaTool.getHWND("script"));
		
		
		
		HWND H = JnaTool.getHWND("TIM_");
	
		
		HWND hwnd = User32.INSTANCE.FindWindow(null, "柃月");
		System.out.println("go::"+User32.INSTANCE.GetWindowThreadProcessId(hwnd, null));
		System.out.println(hwnd);
		
		
		SendMsgToHandle.sendMsgByTitle("柃月", "12341womneyiqi@#");
	}
}
