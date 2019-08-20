SUMMARY = "Package lighttpd app container image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DEPENDS = "tar-native"

SRC_URI = "file://${CONTAINER_NAME}.nspawn"

do_compile[noexec] = "1"

do_install[mcdepends] = "multiconfig::container:${CONTAINER_NAME}:do_image_complete"

CONTAINER_NAME = "app-container-image-lighttpd"
CONTAINER_TMPDIR ?= "${TMPDIR}"
CONTAINER_MACHINE ?= "${MACHINE}"
CONTAINER_DEPLOY_DIR ?= "${CONTAINER_TMPDIR}/deploy/images/${CONTAINER_MACHINE}"

do_install () {
    install -d ${D}/var/lib/machines/${CONTAINER_NAME}
    tar -C ${D}/var/lib/machines/${CONTAINER_NAME} -xf \
        ${CONTAINER_DEPLOY_DIR}/${CONTAINER_NAME}-${CONTAINER_MACHINE}.tar.bz2

    install -d ${D}${sysconfdir}/systemd/nspawn
    install -m 0644 ${WORKDIR}/${CONTAINER_NAME}.nspawn ${D}${sysconfdir}/systemd/nspawn/

    install -d ${D}${sysconfdir}/systemd/system/machines.target.wants
    ln -sf ${systemd_system_unitdir}/systemd-nspawn@.service \
        ${D}${sysconfdir}/systemd/system/machines.target.wants/systemd-nspawn@${CONTAINER_NAME}.service
}

RDEPENDS_${PN} += "systemd-container"

# No need to QA the pre-built binaries
INSANE_SKIP_${PN} += "build-deps dev-so already-stripped"
