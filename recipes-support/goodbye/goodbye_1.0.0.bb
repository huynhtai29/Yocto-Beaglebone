SUMMARY = "VPU Stress examples"

DESCRIPTION = "VPU Stress examples"
SECTION = "examples"

inherit pkgconfig

DEPENDS = "libbasic"
RDEPENDS_${PN} = "libbasic"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
  file://goodbye.c \
"

inherit pkgconfig

do_compile() {
  ${CC} ${WORKDIR}/goodbye.c -o goodbye ${CXXFLAGS} ${LDFLAGS} `pkg-config --cflags --libs gstreamer-1.0`
}

do_install() {
  install -d ${D}${bindir}/
  install -m 0755 ${S}/goodbye ${D}${bindir}/
}

FILES_${PN} += " \
  ${bindir}/ \
"
