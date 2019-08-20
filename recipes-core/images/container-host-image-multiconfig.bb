SUMMARY = "A minimal container host image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit core-image

IMAGE_INSTALL = " \
	packagegroup-core-boot \
"

do_image[mcdepends] = "multiconfig:host:container:app-container-image-lighttpd:do_image_complete"

ROOTFS_POSTPROCESS_COMMAND += "rootfs_install_container ; "

rootfs_install_container () {
    install -d ${IMAGE_ROOTFS}/${localstatedir}/lib/machines
    install ${TOPDIR}/tmp-container/deploy/images/${MACHINE}/app-container-image-lighttpd-${MACHINE}.ext4 \         
        ${IMAGE_ROOTFS}/${localstatedir}/lib/machines
}
