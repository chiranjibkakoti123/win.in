/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.errorprone.annotations;

public final class Modifier
extends Enum<Modifier> {
    private static final Modifier[] $VALUES;
    public static final /* enum */ Modifier ABSTRACT;
    public static final /* enum */ Modifier DEFAULT;
    public static final /* enum */ Modifier FINAL;
    public static final /* enum */ Modifier NATIVE;
    public static final /* enum */ Modifier PRIVATE;
    public static final /* enum */ Modifier PROTECTED;
    public static final /* enum */ Modifier PUBLIC;
    public static final /* enum */ Modifier STATIC;
    public static final /* enum */ Modifier STRICTFP;
    public static final /* enum */ Modifier SYNCHRONIZED;
    public static final /* enum */ Modifier TRANSIENT;
    public static final /* enum */ Modifier VOLATILE;

    static {
        Modifier modifier;
        Modifier modifier2;
        Modifier modifier3;
        Modifier modifier4;
        Modifier modifier5;
        Modifier modifier6;
        Modifier modifier7;
        Modifier modifier8;
        Modifier modifier9;
        Modifier modifier10;
        Modifier modifier11;
        Modifier modifier12;
        PUBLIC = modifier12 = new Modifier();
        PROTECTED = modifier11 = new Modifier();
        PRIVATE = modifier10 = new Modifier();
        ABSTRACT = modifier9 = new Modifier();
        DEFAULT = modifier8 = new Modifier();
        STATIC = modifier7 = new Modifier();
        FINAL = modifier6 = new Modifier();
        TRANSIENT = modifier5 = new Modifier();
        VOLATILE = modifier4 = new Modifier();
        SYNCHRONIZED = modifier3 = new Modifier();
        NATIVE = modifier2 = new Modifier();
        STRICTFP = modifier = new Modifier();
        $VALUES = new Modifier[]{modifier12, modifier11, modifier10, modifier9, modifier8, modifier7, modifier6, modifier5, modifier4, modifier3, modifier2, modifier};
    }

    public static Modifier valueOf(String string2) {
        return (Modifier)Enum.valueOf(Modifier.class, (String)string2);
    }

    public static Modifier[] values() {
        return (Modifier[])$VALUES.clone();
    }
}

