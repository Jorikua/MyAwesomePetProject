package com.example.feed.di

import com.example.base.di.AppComponent
import com.example.base.di.AppComponentApi
import com.example.base.di.scope.PerFeature
import dagger.Component
import java.lang.ref.WeakReference

@PerFeature
@Component(dependencies = [PostsDependencies::class], modules = [PostsDataModule::class])
interface PostsFeatureFeatureComponent : PostsFeatureComponentApi {
  
  companion object {
    @Volatile
    private lateinit var postsFeatureComponentWeak: WeakReference<PostsFeatureFeatureComponent>
    
    fun get(postsDependencies: PostsDependencies): PostsFeatureFeatureComponent {
      if (!this::postsFeatureComponentWeak.isInitialized || postsFeatureComponentWeak.get() == null) {
        synchronized(PostsFeatureFeatureComponent::class) {
          if (!this::postsFeatureComponentWeak.isInitialized || postsFeatureComponentWeak.get() == null) {
            val component = DaggerPostsFeatureFeatureComponent.builder()
                .postsDependencies(postsDependencies)
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
  interface PostsDependenciesComponent : PostsDependencies
}

val postsFeatureComponent: PostsFeatureComponentApi
  get() = PostsFeatureFeatureComponent.get(DaggerPostsFeatureFeatureComponent_PostsDependenciesComponent
      .builder()
      .appComponentApi(AppComponent.get())
      .build())