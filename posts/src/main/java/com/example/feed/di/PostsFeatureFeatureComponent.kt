package com.example.feed.di

import com.example.base.di.AppComponent
import com.example.base.di.AppComponentApi
import com.example.base.di.scope.PerFeature
import dagger.Component
import java.lang.ref.WeakReference

@PerFeature
@Component(dependencies = [PostsFeatureDependencies::class], modules = [PostsFeatureModule::class])
interface PostsFeatureFeatureComponent : PostsFeatureComponentApi {
  
  companion object {
    @Volatile
    private lateinit var postsFeatureComponentWeak: WeakReference<PostsFeatureFeatureComponent>
    
    fun get(postsDependencies: PostsFeatureDependencies): PostsFeatureFeatureComponent {
      if (!this::postsFeatureComponentWeak.isInitialized || postsFeatureComponentWeak.get() == null) {
        synchronized(PostsFeatureFeatureComponent::class) {
          if (!this::postsFeatureComponentWeak.isInitialized || postsFeatureComponentWeak.get() == null) {
            val component = DaggerPostsFeatureFeatureComponent.builder()
                .postsFeatureDependencies(postsDependencies)
                .build()
            postsFeatureComponentWeak = WeakReference(component)
          }
        }
      }
      
      return postsFeatureComponentWeak.get()!!
    }
  }
  
  @PerFeature
  @Component(dependencies = [AppComponentApi::class])
  interface PostsDependenciesComponent : PostsFeatureDependencies
}

val postsFeatureComponent: PostsFeatureComponentApi
  get() = PostsFeatureFeatureComponent.get(DaggerPostsFeatureFeatureComponent_PostsDependenciesComponent
      .builder()
      .appComponentApi(AppComponent.get())
      .build())