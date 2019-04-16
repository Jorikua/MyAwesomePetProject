package com.example.user.di

import com.example.base.di.AppComponent
import com.example.base.di.AppComponentApi
import com.example.base.di.scope.PerFeature
import dagger.Component
import java.lang.ref.WeakReference

@PerFeature
@Component(dependencies = [UserFeatureDependencies::class], modules = [UserFeatureModule::class])
interface UserFeatureComponent : UserFeatureComponentApi {
  
  companion object {
    @Volatile
    private lateinit var userFeatureComponentWeak: WeakReference<UserFeatureComponent>
    
    fun get(userFeatureDependencies: UserFeatureDependencies): UserFeatureComponent {
      if (!this::userFeatureComponentWeak.isInitialized || userFeatureComponentWeak.get() == null) {
        synchronized(UserFeatureComponent::class) {
          if (!this::userFeatureComponentWeak.isInitialized || userFeatureComponentWeak.get() == null) {
            val component = DaggerUserFeatureComponent.builder()
                .userFeatureDependencies(userFeatureDependencies)
                .build()
            userFeatureComponentWeak = WeakReference(component)
          }
        }
      }
      
      return userFeatureComponentWeak.get()!!
    }
  }
  
  @PerFeature
  @Component(dependencies = [AppComponentApi::class])
  interface UserDependenciesComponent : UserFeatureDependencies
}

val userFeatureComponent: UserFeatureComponentApi
  get() = UserFeatureComponent.get(DaggerUserFeatureComponent_UserDependenciesComponent
      .builder()
      .appComponentApi(AppComponent.get())
      .build())