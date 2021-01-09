SUMMARY = "Hello World"

SRC_URI = "file://hello.c \
           file://Makefile \
		"

DEPENDS="libbasic"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INSANE_SKIP_${PN} += "ldflags"
CLEANBROKEN = "1"
S = "${WORKDIR}"

EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' \
   'CFLAGS=${CFLAGS} -I${S}/. -DWITHOUT_XATTR' 'BUILDDIR=${D}${includedir}' 'INC=/code/working/build/tmp/work/armv7at2hf-neon-poky-linux-gnueabi/libbasic/1.0.0-r0/${includedir}' 'LIB=/code/working/build/tmp/work/armv7at2hf-neon-poky-linux-gnueabi/libbasic/1.0.0-r0/image${libdir}/libbasic.so.1.0.0' "

do_compile (){
  oe_runmake
}

do_install () {
   oe_runmake install DESTDIR=${D} BINDIR=${bindir} SBINDIR=${sbindir} \
      MANDIR=${mandir} INCLUDEDIR=${includedir}
}
FILES_SOLIBSDEV = ""
FILES_${PN} += " \
/* \
${libdir}/*.so \
${libdir}/*.so.* \
"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
SOLIBS = ".so"


INSANE_SKIP_${PN} += "already-stripped"
INSANE_SKIP_${PN} += "dev-so"
#For dev packages only
INSANE_SKIP_${PN}-dev = "ldflags"