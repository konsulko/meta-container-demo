SUMMARY = "Package lighttpd app container image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

do_compile[noexec] = "1"

do_install[mcdepends] = "mc::container:app-container-image-lighttpd:do_image_complete"

do_install () {
    install -d ${D}/var/lib/machines
    #install ${TOPDIR}/tmp-container/${DEPLOY_DIR_IMAGE}/app-container-image-lighttpd.ext4
    install ${TOPDIR}/tmp-container/deploy/images/containerx86-64/app-container-image-lighttpd-containerx86-64.ext4 \
        ${D}/var/lib/machines
}

RDEPENDS_${PN} += "systemd-container"
