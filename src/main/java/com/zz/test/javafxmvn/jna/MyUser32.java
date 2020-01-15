package com.zz.test.javafxmvn.jna;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public interface MyUser32 extends StdCallLibrary, WinUser {
	static MyUser32 INSTANCE = (MyUser32) Native
			.loadLibrary("user32", MyUser32.class, W32APIOptions.DEFAULT_OPTIONS);
	int SendMessage(HWND hWnd, int Msg, int wParam, String lParam);
	// http://msdn.microsoft.com/en-us/library/windows/desktop/ms633573(v=vs.85).aspx
	interface WindowProc extends StdCallCallback {
		LRESULT callback(HWND hWnd, int uMsg, WPARAM wParam, LPARAM lParam);
	}
	// http://msdn.microsoft.com/en-us/library/windows/desktop/ms633591(v=vs.85).aspx
	WindowProc SetWindowLongPtr(HWND hWnd, int nIndex, WindowProc dwNewLong);
	// http://msdn.microsoft.com/en-us/library/windows/desktop/ms633584(v=vs.85).aspx
	WindowProc GetWindowLongPtr(HWND hWnd, int nIndex);
}
