package org.tigergrab.javapooh.method.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tigergrab.javapooh.attr.impl.AttributeControl;
import org.tigergrab.javapooh.cp.ConstantInfo;
import org.tigergrab.javapooh.view.impl.Element;

/**
 * u2 access_flags; u2 name_index; u2 descriptor_index; u2 attributes_count;
 * attribute_info attributes[attributes_count];
 */
public class MethodInfo {

	protected final Logger logger = LoggerFactory.getLogger("MethodInfo.class");

	public MethodInfo() {
	}

	public Element getData(final byte[] sourceBytes, final int cursor,
			Element ele) {
		Element result = new Element(ele);
		int index = 0;
		byte[] bt = new byte[ele.getSize()];
		for (int i = cursor; i < cursor + ele.getSize(); i++) {
			bt[index++] = sourceBytes[i];
		}
		result.setBytes(bt);
		return result;
	}

	public int getAttributes(final byte[] bytes,
			final Map<Integer, ConstantInfo> constantPool,
			final int attributesCount, final int cursor) {
		int currentCursor = cursor;
		int count = 1;
		for (;;) {
			if (count < attributesCount + 1) {
				AttributeControl control = new AttributeControl(bytes,
						attributesCount, constantPool);
				currentCursor = control.getInfo(currentCursor);
				count++;
				continue;
			}
			break;
		}
		return currentCursor;
	}
}
