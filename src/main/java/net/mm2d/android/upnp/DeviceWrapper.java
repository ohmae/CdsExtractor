/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.android.upnp;

import net.mm2d.upnp.Device;
import net.mm2d.upnp.Icon;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 特定のDeviceTypeへのインターフェースを備えるDeviceWrapperの共通の親。
 *
 * <p>Deviceはhas-a関係で保持するが、Wrapperと1対1である保証はなく、
 * 複数のWrapperから一つのDeviceが参照される可能性もある。
 * WrapperとしてDeviceへアクセスするためのアクセッサをここで定義し、
 * 継承したクラスではその他の機能を実装する。
 *
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public abstract class DeviceWrapper {
    @Nonnull
    private final Device mDevice;

    private boolean mIconSearched;
    @Nullable
    private Icon mIcon;

    public DeviceWrapper(@Nonnull Device device) {
        mDevice = device;
    }

    /**
     * このクラスがwrapしているDeviceのインスタンスを返す。
     *
     * <p><b>取扱い注意！</b>
     * <p>protectedであるが、内部実装を理解したサブクラスから参照される想定であり、
     * 直接Deviceをサブクラス以外から参照しないこと。
     * Deviceの各プロパティへは用意されたアクセッサを利用する。
     *
     * @return Device
     */
    @Nonnull
    public Device getDevice() {
        return mDevice;
    }

    /**
     * Deviceの有効なIconを返す。
     *
     * <p>MsControlPointにてダウンロードするIconを一つのみに限定しているため、
     * Binaryデータがダウンロード済みのものがあればそれを返す。
     *
     * @return Iconインスタンス、ない場合はnullが返る。
     */
    @Nullable
    public Icon getIcon() {
        if (mIconSearched) {
            return mIcon;
        }
        mIconSearched = true;
        mIcon = searchIcon();
        return mIcon;
    }

    @Nullable
    private Icon searchIcon() {
        final List<Icon> iconList = mDevice.getIconList();
        for (final Icon icon : iconList) {
            if (icon.getBinary() != null) {
                return icon;
            }
        }
        return null;
    }

    /**
     * Locationに記述のIPアドレスを返す。
     *
     * 記述に異常がある場合は空文字が返る。
     *
     * @return IPアドレス
     */
    @Nonnull
    public String getIpAddress() {
        return mDevice.getIpAddress();
    }

    /**
     * UDNタグの値を返す。
     *
     * @return UDNタグの値
     */
    @Nonnull
    public String getUdn() {
        return mDevice.getUdn();
    }

    /**
     * friendlyNameタグの値を返す。
     *
     * @return friendlyNameタグの値
     */
    @Nonnull
    public String getFriendlyName() {
        return mDevice.getFriendlyName();
    }

    /**
     * manufacturerタグの値を返す。
     *
     * <p>値が存在しない場合nullが返る。
     *
     * @return manufacturerタグの値
     */
    @Nullable
    public String getManufacture() {
        return mDevice.getManufacture();
    }

    /**
     * manufacturerURLタグの値を返す。
     *
     * <p>値が存在しない場合nullが返る。
     *
     * @return manufacturerURLタグの値
     */
    @Nullable
    public String getManufactureUrl() {
        return mDevice.getManufactureUrl();
    }

    /**
     * modelNameタグの値を返す。
     *
     * @return modelNameタグの値
     */
    @Nonnull
    public String getModelName() {
        return mDevice.getModelName();
    }

    /**
     * modelURLタグの値を返す。
     *
     * <p>値が存在しない場合nullが返る。
     *
     * @return modelURLタグの値
     */
    @Nullable
    public String getModelUrl() {
        return mDevice.getModelUrl();
    }

    /**
     * modelDescriptionタグの値を返す。
     *
     * <p>値が存在しない場合nullが返る。
     *
     * @return modelDescriptionタグの値
     */
    @Nullable
    public String getModelDescription() {
        return mDevice.getModelDescription();
    }

    /**
     * modelNumberタグの値を返す。
     *
     * <p>値が存在しない場合nullが返る。
     *
     * @return modelNumberタグの値
     */
    @Nullable
    public String getModelNumber() {
        return mDevice.getModelNumber();
    }

    /**
     * serialNumberタグの値を返す。
     *
     * <p>値が存在しない場合nullが返る。
     *
     * @return serialNumberタグの値
     */
    @Nullable
    public String getSerialNumber() {
        return mDevice.getSerialNumber();
    }

    /**
     * presentationURLタグの値を返す。
     *
     * <p>値が存在しない場合nullが返る。
     *
     * @return presentationURLタグの値
     */
    @Nullable
    public String getPresentationUrl() {
        return mDevice.getPresentationUrl();
    }

    /**
     * SSDPパケットに記述されているLocationヘッダの値を返す。
     *
     * @return Locationヘッダの値
     */
    @Nullable
    public String getLocation() {
        return mDevice.getLocation();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DeviceWrapper)) {
            return false;
        }
        final DeviceWrapper m = (DeviceWrapper) o;
        return mDevice.equals(m.mDevice);
    }

    @Override
    public int hashCode() {
        return mDevice.hashCode();
    }

    @Override
    public String toString() {
        return getFriendlyName();
    }
}
