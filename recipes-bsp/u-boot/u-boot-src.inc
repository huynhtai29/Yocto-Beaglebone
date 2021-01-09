require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc
require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot-common.inc

HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"

DEPENDS += "dtc-native bc-native flex-native bison-native"

SUMMARY = "U-Boot bootloader for Keem Bay Platform"

DEPENDS += "${@bb.utils.contains('DM_VERITY','true','u-boot-mkimage-native','',d)}"

UBOOT_SUFFIX ?= "img"
SPL_BINARY ?= "MLO"
EXTRA_OEMAKE += "${PACKAGECONFIG_CONFARGS}"

#PROVIDES = ""
#PROVIDES += "u-boot"
#PKG_${PN} = "u-boot"
#PKG_${PN}-dev = "u-boot-dev"
#PKG_${PN}-dbg = "u-boot-dbg"

PACKAGE_ARCH = "${MACHINE_ARCH}"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "0b0c6af38738f2c132cfd41a240889acaa031c8f"

SRC_URI = "git://git.denx.de/u-boot.git;protocol=git;branch=master \
"

PV = "v2020.01+git${SRCPV}+r44"

S = "${WORKDIR}/git"

# Disable mender variable checking
do_check_mender_defines[noexec] = "1"
