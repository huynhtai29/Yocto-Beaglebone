SUMMARY = "Install dynamic share library into image "

SRC_URI = "file://src/goodbye.c \
	        file://src/hello.c \
	        file://inc/goodbye.h \
	        file://inc/hello.h \ 
		file://main.c \	
	        file://build \
                file://Makefile \
                "

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INSANE_SKIP_${PN} += "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"  
CLEANBROKEN = "1"

INSANE_SKIP_${PN}-dev = "arch ldflags staticdev file-rdeps"

S = "${WORKDIR}"

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg ${PN}-staticdev"

FILES_${PN} += "${libdir}/*.so*"
FILES_${PN}-dev = "{libdir}/*.so* {includedir}/libbasic/*.h"

PACKAGES += "${PN}-bash-completion"

PACKAGECONFIG ??= "${@bb.utils.contains('PTEST_ENABLED', '1', 'tests', '', d)} \
                   "

PACKAGECONFIG[debug] = "--enable-debug,--disable-debug"
PACKAGECONFIG[tests] = "--enable-tests,--disable-tests"
PACKAGECONFIG[valgrind] = "--enable-valgrind,--disable-valgrind,valgrind,"
PACKAGECONFIG[gst-tracer-hooks] = "--enable-gst-tracer-hooks,--disable-gst-tracer-hooks,"
PACKAGECONFIG[unwind] = "--with-unwind,--without-unwind,libunwind"
PACKAGECONFIG[dw] = "--with-dw,--without-dw,elfutils"

EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' \
   'CFLAGS=${CFLAGS} -I${S}/. -DWITHOUT_XATTR' 'BUILDDIR=${S}' 'DIR_LIB=-Llibbasic'"

EXTRA_OEMAKE += "${@bb.utils.contains('PACKAGECONFIG', 'manpages', '', 'OE_DISABLE_MANPAGES=1', d)}"

do_compile (){
  oe_runmake lib
}

do_install () {
   oe_runmake hello
   install -m 0755 -d ${D}${libdir}
   install -m 755  ${WORKDIR}/libbasic.so.1.0.0 ${D}${libdir}
   install -m 0755 -d ${D}${includedir}/libbasic
   install -m 0775 ${S}/inc/*.h ${D}${includedir}/libbasic
   install -m 0755 -d ${D}${bindir}
   install -m 0775 ${S}/hello ${D}${bindir}
   sed -e 's,--sysroot=${STAGING_DIR_TARGET},,g'
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

