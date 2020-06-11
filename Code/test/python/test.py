from enum import Enum
CameraModels = Enum('CameraModels', 'INTEL_R200 INTEL_D435')
CameraRoles = Enum('CameraRoles', 'LR_Left LR_Right TF_Floor TF_Top')
CameraTypes = Enum('CameraTypes', 'D435_LR_Left D435_LR_Right D435_TF_Floor D435_TF_Top R200_TF_Floor R200_TF_Top')


class CameraInfo():
    model = None
    Roles = None


cam1 = CameraInfo()
cam1.model = CameraModels.INTEL_R200

print(cam1.model)
