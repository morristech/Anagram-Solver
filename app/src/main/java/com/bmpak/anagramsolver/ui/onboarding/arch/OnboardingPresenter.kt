package com.bmpak.anagramsolver.ui.onboarding.arch

import com.bmpak.anagramsolver.framework.arch.Presenter
import com.bmpak.anagramsolver.framework.navigator.Navigator
import com.bmpak.anagramsolver.model.Dictionary
import com.bmpak.anagramsolver.ui.onboarding.arch.OnboardingStep.DOWNLOAD_LANGUAGES
import com.bmpak.anagramsolver.ui.onboarding.arch.OnboardingStep.INSTALL_LANGUAGE
import com.bmpak.anagramsolver.ui.onboarding.arch.OnboardingStep.PICKING_LANGUAGE

class OnboardingPresenter(
    private val navigator: Navigator
) : Presenter<OnboardingView>() {

  var viewModel: OnboardingViewModel = OnboardingViewModel.INITIAL
    private set

  fun dictionaryClicked(dictionary: Dictionary) {
    val pickedDictionaries = viewModel.pickedDictionaries.toMutableMap()
    pickedDictionaries[dictionary] = pickedDictionaries[dictionary]?.not() ?: false
    this.viewModel = viewModel.copy(pickedDictionaries = pickedDictionaries.toMap())
    viewWRef.get()?.bind(viewModel)
  }

  fun installStepClicked() {
    this.viewModel = viewModel.copy(currentStep = DOWNLOAD_LANGUAGES)
    viewWRef.get()?.showDownloadingFeedback()
    viewWRef.get()?.bind(viewModel)
  }

  fun downloadFinished() {
    this.viewModel = viewModel.copy(currentStep = INSTALL_LANGUAGE)
    viewWRef.get()?.bind(viewModel)
  }

  fun initialOnboardingAnimationEnd() {
    this.viewModel = viewModel.copy(currentStep = PICKING_LANGUAGE)
    viewWRef.get()?.bind(viewModel)
  }

}