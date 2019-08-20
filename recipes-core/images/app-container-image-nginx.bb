SUMMARY = "A nginx container image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

require app-container-image.bb

IMAGE_INSTALL += "nginx"

# Add /var/log/nginx and /run/nginx
ROOTFS_POSTPROCESS_COMMAND += "rootfs_add_nginx_dirs ; "

rootfs_add_nginx_dirs () {
    install -m 755 -d ${IMAGE_ROOTFS}/${localstatedir}/log/nginx
    install -m 755 -d ${IMAGE_ROOTFS}/run/nginx
} 
