//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.securinets.utils;

import com.securinets.services.Author;
import com.securinets.services.LooseSecurity;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Comparator;

public class QuestionCompar implements Serializable, Comparator<Object> {
    private String internal;
    private static final long serialVersionUID = 1L;

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public QuestionCompar() {
    }

    public int compare(Object one, Object two) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(this.internal);
            ByteArrayInputStream ini = new ByteArrayInputStream(decodedBytes);
            LooseSecurity a = new LooseSecurity(ini);
            Author res = (Author)a.readObject();
            return res.old == 1 ? 1 : 0;
        } catch (ClassNotFoundException | IOException var7) {
            var7.printStackTrace();
            return -1;
        }
    }
}
